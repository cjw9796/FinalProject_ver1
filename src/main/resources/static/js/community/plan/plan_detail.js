const ctn1deslist1 = document.querySelector('.ctn1_des_li');
const hujitongSvg = document.querySelector('.ctn1_des_li .sc_des_mark_hujitong > svg');
const pencilSvg = document.querySelector('.ctn1_des_li .pencil > svg');

ctn1deslist1.addEventListener('mouseenter', () => {
    hujitongSvg.style.fill = '#df5368';
    pencilSvg.style.fill = '#00B8FF';
    ctn1deslist1.style.fill = '#00B8FF'; // 클래스에 스타일을 적용하는 것이 아니라 직접 fill 색상 변경
}, false);

ctn1deslist1.addEventListener('mouseleave', () => {
    hujitongSvg.style.fill = '#ffffff';
    pencilSvg.style.fill = '#ffffff';
    ctn1deslist1.style.fill = '#E9E9E9'; // 클래스에 스타일을 적용하는 것이 아니라 직접 fill 색상 변경
}, false);


const ctn1deslist2 = document.querySelector('.ctn1_des_li2');
const hujitongSvg2 = document.querySelector('.ctn1_des_li2 .sc_des_mark_hujitong > svg');
const pencilSvg2 = document.querySelector('.ctn1_des_li2 .pencil > svg');

ctn1deslist2.addEventListener('mouseenter', () => {
    hujitongSvg2.style.fill = '#df5368';
    pencilSvg2.style.fill = '#00B8FF';
    ctn1deslist2.style.fill = '#00B8FF'; // 클래스에 스타일을 적용하는 것이 아니라 직접 fill 색상 변경
}, false);

ctn1deslist2.addEventListener('mouseleave', () => {
    hujitongSvg2.style.fill = '#ffffff';
    pencilSvg2.style.fill = '#ffffff';
    ctn1deslist2.style.fill = '#E9E9E9'; // 클래스에 스타일을 적용하는 것이 아니라 직접 fill 색상 변경
}, false);


