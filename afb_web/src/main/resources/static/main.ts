window.addEventListener('DOMContentLoaded', function() {
    document.querySelector("#follow_form_button").addEventListener('click', (event: HTMLElementEvent<HTMLButtonElement>) => followForm(event, '/api/follow_keyword/'));
}, false);

interface HTMLElementEvent<T extends HTMLElement> extends Event {
    target: T;
}

const followForm = (event, url) => {
    // reset
    resetModal;
    document.getElementById("modalCenterTitle").innerText = "フォロー条件";
    const modalContentElement = document.getElementById("modal-body");
    modalContentElement.innerHTML = null;

    fetch(`${url}`, {method: 'GET'})
        .catch(e => alert("エラーが発生しました。もう一度試してください。"))
        .then((response: Response) => response.json())
        .then((json: JSON) => {
            const status = json['statusCode'];
            if (status === 401) {
                resetModal();
                (<HTMLButtonElement>document.getElementById('close-button')).click;
                alert("ログインしてください。");
            }
            const keyword = json["keyword"];
            (<HTMLInputElement>document.getElementById("keyword")).value = keyword;
        });

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
    formButton.addEventListener('click', (ev: HTMLElementEvent<HTMLButtonElement>) => postFollow(ev));

    return true
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
        .catch(e => alert("エラーが発生しました。もう一度試してください。"))
        .then((response: Response) => response.json())
        .then((json: JSON) => {
            const status = json['statusCode'];
            if (status === 400) alert("キーワードを入力してください。");
            else if (status === 401) {
                resetModal();
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
                    .catch(e => alert("エラーが発生しました。もう一度試してください。"))
                    .then((response: Response) => response.json())
                    .then((json: JSON) => {
                        // reset
                        const status = json['statusCode'];
                        if (status === 400) alert("キーワードを入力してください。");
                        else if (status === 403) {
                            resetModal();
                            (<HTMLButtonElement>document.getElementById('close-button')).click;
                            alert("ログインしてください。");

                        }
                        else if (status === 200) {
                            resetModal();
                            (<HTMLButtonElement>document.getElementById('close-button')).click;
                            alert(`${json['followCount']}人フォローしました。`);
                        }
                    });
            }
        });
    return null
};

const resetModal = () => {
    document.getElementById("modalCenterTitle").innerText = null;
    const modalContentElement = document.getElementById("modal-body");
    modalContentElement.innerHTML = null;

    const formButton = document.getElementById("form-button");
    formButton.innerText = null;
};