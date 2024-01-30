/*
 * 우편번호 검색
 */
	function findAddr() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            let roadAddr = data.roadAddress;	// 도로명 주소
	            let jibunAddr = data.jibunAddress;	// 지번 주소
	            let extraAddr ='';
	            
	            document.getElementById('postcode').value = data.zonecode;	// 우편번호
	            
	            if(data.userSelectedType == 'R') {
            		if(data.bname != '') {
            			extraAddr += data.bname;	// 동이름
            		}
            		if(data.buildingName != '') {
            			extraAddr += ', ' + data.buildingName;		//빌딩명
            		}	
	            	roadAddr += extraAddr != '' ? '(' + extraAddr + ')' : '';	// 동이름이나 빌딩명이 있으면 (동이름, 빌딩명)으로 뒤에 부착
	            	document.getElementById('addr').value = roadAddr;	// 도로명(동이름, 빌딩명) 을 input에 넣는다
	            } else {
            		if(data.buildingName != '') {
            			extraAddr += data.buildingName;
            		}
	            	jibunAddr += extraAddr != '' ? '(' + extraAddr + ')' : '';
	            	document.getElementById('addr').value = jibunAddr;
	            }
	            document.getElementById('detailAddr').focus();
	        }
	    }).open();
	}