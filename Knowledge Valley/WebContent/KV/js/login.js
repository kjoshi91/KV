function checkLogin(){
	//window.location.replace("adminTest.html");
	var emailId=document.getElementById("emailInput").value;
	var pass=document.getElementById("passwordInput").value;
	jQuery.ajax({
        url: '../../LoginRequest',
        type: 'POST',
        data: '{emailId:"'+emailId+'",passwd:"'+pass+'",action:"login"}',
        dataType: 'json',
        beforeSend: function(x) {
          
        },
        success: function(jsonObj) {
      	  var response=jsonObj.result;
      	  if(response=='success')
      	  {
      		  localStorage.setItem("result", ""+response);
      		  window.location.replace("adminTest.html");
      		  //onLoginSuccess();
      		  //alert("Is admin: "+isAdmin);
      		  /*if(isAdmin==1)
      		  {
      			  var hostname=window.location.hostname;
      			  var port=window.location.port;
      			  var URL="http://"+hostname+":"+port+"/BK_Site/NVC/adminPortal.html";
      			  var openedTab=window.open(URL);
      			  localStorage.setItem("isAdmin",isAdmin);
      		  }*/
      	  }
      		  
      	  else
      		  shakeModal();
        }
  });
}

function logout()
{
	localStorage.removeItem("result");
	window.location.replace("adminLogin.html");
}