function fetchContacts()
{
	var contact1;
	var contact2;
	
	jQuery.ajax({
        url: '../../ContactsServlet',
        type: 'POST',
        data: '{cont1:"",cont2:"",action:"fetch"}',
        dataType: 'json',
        beforeSend: function(x) {
          
        },
        success: function(jsonObj) {
      	  var response=jsonObj.action;
      	  if(response=='fetched')
      	  {
      		  document.getElementById("phone1").value=jsonObj.con1;
      		  document.getElementById("phone2").value=jsonObj.con2;
       	  }
      		  
      	  else
      		  shakeModal();
        }
  });
}