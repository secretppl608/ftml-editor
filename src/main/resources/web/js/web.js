const editor = document.getElementById('editor');
const tpl = document.getElementById('tpl');
class Tpl extends HTMLElement {
    constructor() {
        super();
        const shadow = this.attachShadow({mode: 'closed'});
        this.shadow = shadow;
        const content = tpl.content.cloneNode(true);
        shadow.appendChild(content);
        this.shadow.getElementById("colorButton").addEventListener("change", (e) => {
            this.shadow.getElementById("cb").style.setProperty('--current-color',e.target.value);
        });
        this.shadow.getElementById("sizeChoose").addEventListener("focus", () => {
            this.shadow.getElementById("textsize-show").classList.add("focus");
        });
        this.shadow.getElementById("sizeChoose").addEventListener("blur", () => {
            this.shadow.getElementById("textsize-show").classList.remove("focus");
        });
        this.shadow.getElementById("sizeChoose").addEventListener("input", (e) => {
            let ele = this.shadow.getElementById("textsize-show");
            if (e.target.value.length > 8) {
                e.target.value = e.target.value.slice(0, 8);
            }
            let content = e.target.value;
            let r = "";
            for (let i of content) {
                r += `<span class="char">${i}</span>`;
            }
            ele.innerHTML = r;
            let allow = new Set(["%", "em", "px", "rem"]);
            for (let p of allow) {
                if (content.endsWith(p)) {
                    if (/^[0-9.]{0,3}$/.test(content.replace(p, ""))) {
                        ele.style.textDecoration = "none";
                        this.shadow.getElementById("sizeChoose").title = "字号";
                        break;
                    }
                } else {
                    ele.style.textDecoration = "red wavy underline";
                    this.shadow.getElementById("sizeChoose").title =
                        "无法被解析的单位";
                }
            }
        });
    }
}

window.customElements.define('tpl-elements', Tpl);
const ele = document.createElement('tpl-elements');
document.addEventListener('contextmenu', (e) => {
    // 在clientX,clientY位置弹出工具组，并将加粗结果回调给JAVA
    e.preventDefault()
    e.stopPropagation()
    let x = e.clientX;
    let y = e.clientY;
    let body = document.querySelector('body');
    if (body.getElementsByTagName(ele.tagName).length === 0){
        body.appendChild(ele);
        ele.style.position = 'absolute';
    }
    ele.style.top = y+'px';
    ele.style.left = x+'px';
})