// Hàm ẩn hiện trình độ tiến nhật
function toggle(){
    var quali = document.getElementById("quali");
    var start = document.getElementById("start");
    var end = document.getElementById("end");
    var total = document.getElementById("total");
    if(quali.style.display == "none") {
        quali.style.display = "";
        start.style.display = "";
        end.style.display = "";
        total.style.display = "";
    } else {
        quali.style.display = "none";
        start.style.display = "none";
        end.style.display = "none";
        total.style.display = "none";
    }
} 
// Hàm xác nhận có xáo hay không
function onclickDelete(messageConfrim) {
	var result=confirm(messageConfrim);
	if(result){
		// lấy form từ MH ADM005
		document.getElementById("inputform").action="DeleteUser.do";
		document.getElementById("inputform").method="POST";
		document.getElementById("inputform").submit();
      } 
}
