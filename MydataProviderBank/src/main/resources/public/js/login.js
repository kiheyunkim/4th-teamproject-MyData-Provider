$(document).ready(()=>{
	$("#login_input").click(()=>{
		let id = $('#idInput').val();
		let pw = $('#pwInput').val();
		let sendData = {id, pw};
		$.post('./loginRequest',sendData,(result)=>{
			if(result.result === 'login_notMatch'){
				alert('아이디와 패스워드가 일치하지 않거나 가입된 계정이 아닙니다.');
			}else if(result.result === 'login_error'){
				alert('에러가 발생했습니다');
			}
			else if(result.result === 'login_ok'){
				window.location.href = '/';
			}
		});
	})
});