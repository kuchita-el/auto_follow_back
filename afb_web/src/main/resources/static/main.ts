window.addEventListener('DOMContentLoaded', function() {
    document.querySelector("#follow_form_button").addEventListener('click', (event: HTMLElementEvent<HTMLButtonElement>) => followForm(event));
    document.querySelector("#config_form_button").addEventListener('click', (event: HTMLElementEvent<HTMLButtonElement>) => configForm(event));
}, false);

interface HTMLElementEvent<T extends HTMLElement> extends Event {
    target: T;
}

const followForm = (event) => {
    // reset
    resetModal(null);
    document.getElementById("modalCenterTitle").innerText = "フォロー条件";

    const modalContentElement = document.getElementById("modal-body");
    const form = <HTMLFormElement>document.createElement('form');
    form.id = "follow_form";

    const keywordInput = <HTMLInputElement>document.createElement('input');
    keywordInput.id = "keyword";
    keywordInput.name = "keyword";
    keywordInput.type = "text";
    form.appendChild(keywordInput);

    modalContentElement.appendChild(form);

    const formButton = document.getElementById("form-button");
    formButton.innerText = "フォロバする";

    const listener = (ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev);
    formButton.addEventListener('click', listener);


    fetch('/api/follow_keyword/', {method: 'GET'})
        .catch(e => alert("エラーが発生しました。もう一度試してください。"))
        .then((response: Response) => response.json())
        .then((json: JSON) => {
            const status = json['statusCode'];
            if (status === 401) {
                resetModal(listener);
                (<HTMLButtonElement>document.getElementById('close-button')).click;
                alert("ログインしてください。");
            }
            const keyword = json["keyword"];
            (<HTMLInputElement>document.getElementById("keyword")).value = keyword;
        });
};

const postFollow = (event: HTMLElementEvent<HTMLButtonElement>) => {
    const keyword = (<HTMLInputElement>document.getElementById("keyword")).value;
    const data = JSON.stringify({'keyword': keyword});

    const token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const c_header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    fetch("/api/follow_keyword/", {method: 'PATCH', headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            [c_header]: token
        }, body: data})
        .catch(e => {
            alert("エラーが発生しました。もう一度試してください。");
            resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev));
        })
        .then((response: Response) => response.json())
        .then((json: JSON) => {
            const status = json['statusCode'];
            if (status === 400) alert("キーワードを入力してください。");
            else if (status === 401) {
                resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev));
                (<HTMLButtonElement>document.getElementById('close-button')).click;
                alert("ログインしてください。");
            }
            else if (status === 200) {
                fetch("/api/follow/", {
                    method: 'POST', headers: {
                        'Content-Type': 'application/json;charset=UTF-8',
                        [c_header]: token
                    }, body: data
                })
                    .catch(e => {
                        resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev));
                        alert("エラーが発生しました。もう一度試してください。")
                    })
                    .then((response: Response) => response.json())
                    .then((json: JSON) => {
                        // reset
                        const status = json['statusCode'];
                        if (status === 400) alert("キーワードを入力してください。");
                        else if (status === 403) {
                            resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev));
                            (<HTMLButtonElement>document.getElementById('close-button')).click;
                            alert("ログインしてください。");

                        }
                        else if (status === 200) {
                            resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev));
                            (<HTMLButtonElement>document.getElementById('close-button')).click;
                            alert(`${json['followCount']}人フォローしました。`);
                        }
                    });
            }
        });
    return
};

const configForm = (event: HTMLElementEvent<HTMLButtonElement>) => {
    // reset
    resetModal;
    document.getElementById("modalCenterTitle").innerText = "フォロー条件";
    const modalContentElement = document.getElementById("modal-body");
    modalContentElement.innerHTML = null;

    const form = <HTMLFormElement>document.createElement('form');
    form.id = "follow_form";

    const keywordInput = <HTMLInputElement>document.createElement('input');
    keywordInput.id = "keyword";
    keywordInput.name = "keyword";
    keywordInput.type = "text";
    form.appendChild(keywordInput);

    const isScheduledInput = <HTMLInputElement>document.createElement('input');
    isScheduledInput.id = "scheduled";
    isScheduledInput.name = "scheduled";
    isScheduledInput.type = "checkbox";
    form.appendChild(isScheduledInput);

    modalContentElement.appendChild(form);

    const formButton = document.getElementById("form-button");
    formButton.innerText = "設定する";
    const listener = (ev: HTMLElementEvent<HTMLButtonElement>) => postConfig(ev);
    formButton.addEventListener('click', listener);

    fetch('/api/config/', {method: 'GET'})
        .catch(e => {
            resetModal(listener);
            alert("エラーが発生しました。もう一度試してください。");
        })
        .then((response: Response) => response.json())
        .then((json: JSON) => {
            const status = json['statusCode'];
            if (status === 401) {
                resetModal(listener);
                (<HTMLButtonElement>document.getElementById('close-button')).click;
                alert("ログインしてください。");
            }
            const keyword = json["keyword"];
            (<HTMLInputElement>document.getElementById("keyword")).value = keyword;
            const scheduled = json['scheduled'];
            if (scheduled === true) {
                (<HTMLInputElement>document.getElementById("scheduled")).checked = true;
            }
        });
};

const postConfig = (event: HTMLElementEvent<HTMLButtonElement>) => {
    const keyword = (<HTMLInputElement>document.getElementById("keyword")).value;
    const scheduled = (<HTMLInputElement>document.getElementById("scheduled")).checked;
    const data = JSON.stringify({'keyword': keyword, 'scheduled': scheduled});

    const token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    const c_header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");

    fetch("api/config/", {method: 'PATCH', headers: {
            'Content-Type': 'application/json;charset=UTF-8',
            [c_header]: token
        }, body: data})
        .catch(e => {
            alert("エラーが発生しました。もう一度試してください。");
            resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postConfig(ev));
        })
        .then((response: Response) => response.json())
        .then((json: JSON) => {
            const status = json['statusCode'];
            if (status === 400) alert("キーワードを入力してください。");
            else if (status === 401) {
                resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postConfig(ev));
                (<HTMLButtonElement>document.getElementById('close-button')).click;
                alert("ログインしてください。");
            }
            else if (status === 200) {
                resetModal((ev: HTMLElementEvent<HTMLButtonElement>) => postConfig(ev));
                (<HTMLButtonElement>document.getElementById('close-button')).click;
                alert("設定を保存しました。");
            }
        });
    return};

const resetModal = (listener: EventListener) => {
    document.getElementById("modalCenterTitle").innerText = null;
    const modalContentElement = document.getElementById("modal-body");
    modalContentElement.innerHTML = null;

    const formButton = document.getElementById("form-button");
    formButton.innerText = null;
    if (listener !== null) {
        formButton.removeEventListener('click', listener)
    }
};