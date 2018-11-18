window.addEventListener('DOMContentLoaded', function () {
    document.querySelector("#follow_form_button").addEventListener('click', function (event) { return followForm(event); });
    document.querySelector("#config_form_button").addEventListener('click', function (event) { return configForm(event); });
}, false);
var followForm = function (event) {
    // reset
    resetModal(null);
    document.getElementById("modalCenterTitle").innerText = "フォロー条件";
    var modalContentElement = document.getElementById("modal-body");
    var form = document.createElement('form');
    form.id = "follow_form";
    var keywordInput = createInputTextElement('keyword', 'キーワード');
    form.appendChild(keywordInput);
    modalContentElement.appendChild(form);
    var formButton = document.getElementById("form-button");
    formButton.innerText = "フォロバする";
    var listener = function (ev) { return postFollow(ev); };
    formButton.addEventListener('click', listener);
    fetch('/api/follow_keyword/', { method: 'GET' })
        .catch(function (e) {
        closeModal(listener);
        alert("エラーが発生しました。もう一度試してください。");
    })
        .then(function (response) { return response.json(); })
        .then(function (json) {
        var status = json['statusCode'];
        if (status === 401) {
            closeModal(listener);
            alert("ログインしてください。");
        }
        var keyword = json["keyword"];
        document.getElementById("keyword").value = keyword;
    });
};
var postFollow = function (event) {
    var _a;
    var keyword = document.getElementById("keyword").value;
    var data = JSON.stringify({ 'keyword': keyword });
    var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    var c_header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
    fetch("/api/follow_keyword/", {
        method: 'PATCH', headers: (_a = {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            _a[c_header] = token,
            _a), body: data
    })
        .catch(function (e) {
        alert("エラーが発生しました。もう一度試してください。");
    })
        .then(function (response) { return response.json(); })
        .then(function (json) {
        var _a;
        var status = json['statusCode'];
        if (status === 400)
            alert("キーワードを入力してください。");
        else if (status === 401) {
            closeModal(function (ev) { return postFollow(ev); });
            alert("ログインしてください。");
        }
        else if (status === 200) {
            fetch("/api/follow/", {
                method: 'POST', headers: (_a = {
                        'Content-Type': 'application/json;charset=UTF-8'
                    },
                    _a[c_header] = token,
                    _a), body: data
            })
                .catch(function (e) {
                alert("エラーが発生しました。もう一度試してください。");
            })
                .then(function (response) { return response.json(); })
                .then(function (json) {
                // reset
                var status = json['statusCode'];
                if (status === 400)
                    alert("キーワードを入力してください。");
                else if (status === 403) {
                    closeModal(function (ev) { return postFollow(ev); });
                    alert("ログインしてください。");
                }
                else if (status === 200) {
                    closeModal(function (ev) { return postFollow(ev); });
                    alert(json['followCount'] + "\u4EBA\u30D5\u30A9\u30ED\u30FC\u3057\u307E\u3057\u305F\u3002");
                }
            });
        }
    });
    return;
};
var configForm = function (event) {
    // reset
    resetModal;
    document.getElementById("modalCenterTitle").innerText = "フォロー条件";
    var modalContentElement = document.getElementById("modal-body");
    modalContentElement.innerHTML = null;
    var form = document.createElement('form');
    form.id = "follow_form";
    var keywordInput = createInputTextElement('keyword', 'キーワード');
    form.appendChild(keywordInput);
    var isScheduledInput = createInputCheckboxElement('scheduled', 'フォロバを自動で行う');
    form.appendChild(isScheduledInput);
    modalContentElement.appendChild(form);
    var formButton = document.getElementById("form-button");
    formButton.innerText = "設定する";
    var listener = function (ev) { return postConfig(ev); };
    formButton.addEventListener('click', listener);
    fetch('/api/config/', { method: 'GET' })
        .catch(function (e) {
        closeModal(listener);
        alert("エラーが発生しました。もう一度試してください。");
    })
        .then(function (response) { return response.json(); })
        .then(function (json) {
        var status = json['statusCode'];
        if (status === 401) {
            closeModal(listener);
            alert("ログインしてください。");
        }
        var keyword = json["keyword"];
        document.getElementById("keyword").value = keyword;
        var scheduled = json['scheduled'];
        if (scheduled === true) {
            document.getElementById("scheduled").checked = true;
        }
    });
};
var postConfig = function (event) {
    var _a;
    var keyword = document.getElementById("keyword").value;
    var scheduled = document.getElementById("scheduled").checked;
    var data = JSON.stringify({ 'keyword': keyword, 'scheduled': scheduled });
    var token = document.querySelector("meta[name='_csrf']").getAttribute("content");
    var c_header = document.querySelector("meta[name='_csrf_header']").getAttribute("content");
    fetch("api/config/", {
        method: 'PATCH', headers: (_a = {
                'Content-Type': 'application/json;charset=UTF-8'
            },
            _a[c_header] = token,
            _a), body: data
    })
        .catch(function (e) {
        alert("エラーが発生しました。もう一度試してください。");
    })
        .then(function (response) { return response.json(); })
        .then(function (json) {
        var status = json['statusCode'];
        if (status === 400)
            alert("キーワードを入力してください。");
        else if (status === 401) {
            closeModal(function (ev) { return postConfig(ev); });
            alert("ログインしてください。");
        }
        else if (status === 200) {
            closeModal(function (ev) { return postConfig(ev); });
            alert("設定を保存しました。");
        }
    });
    return;
};
var resetModal = function (listener) {
    document.getElementById("modalCenterTitle").innerText = null;
    var modalContentElement = document.getElementById("modal-body");
    modalContentElement.innerHTML = null;
    var formButton = document.getElementById("form-button");
    formButton.innerText = null;
    if (listener !== null) {
        formButton.removeEventListener('click', listener);
    }
};
var createInputTextElement = function (name, label) {
    var formWrapper = document.createElement('div');
    formWrapper.className = 'form-group';
    var labelElement = document.createElement('label');
    labelElement.htmlFor = name;
    labelElement.innerText = label;
    formWrapper.appendChild(labelElement);
    var inputElement = document.createElement('input');
    inputElement.name = name;
    inputElement.id = name;
    inputElement.type = 'text';
    inputElement.className = 'form-control';
    formWrapper.appendChild(inputElement);
    return formWrapper;
};
var createInputCheckboxElement = function (name, label) {
    var formWrapper = document.createElement('div');
    formWrapper.className = 'form-group form-check';
    var inputElement = document.createElement('input');
    inputElement.name = name;
    inputElement.id = name;
    inputElement.type = 'checkbox';
    inputElement.className = 'form-check-input';
    formWrapper.appendChild(inputElement);
    var labelElement = document.createElement('label');
    labelElement.htmlFor = name;
    labelElement.className = 'form-check-label';
    labelElement.innerText = label;
    formWrapper.appendChild(labelElement);
    return formWrapper;
};
var closeModal = function (listener) {
    document.getElementById('close-button').click();
    resetModal(listener);
};
