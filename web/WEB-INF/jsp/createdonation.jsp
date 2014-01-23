<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-1.9.2.css"/>" />
        <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-timepicker.css"/>" />
        <link rel="stylesheet" href="<c:url value="/resources/css/style_horizontal_panel.css"/>" />
        <link rel="stylesheet" href="<c:url value="/resources/css/admin_style.css"/>" />

        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.4/jquery.min.js"></script>
        <script type="text/javascript">
            $(function() {
                /* For zebra striping */
                $("table tr:nth-child(odd)").addClass("odd-row");
                /* For cell text alignment */
                $("table td:first-child, table th:first-child").addClass("first");
                /* For removing the last border */
                $("table td:last-child, table th:last-child").addClass("last");
            });
        </script>

        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.min.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.9.2.js"/>"></script>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-timepicker.js"/>"></script>


        <script type="text/javascript">
            $(function() {
                $("input[name='endDate']").datepicker({dateFormat: "yy-mm-dd", changeYear: true, changeMonth: true});
            });
        </script>

        <script type="text/javascript">
            function PreviewImage() {

                oFReader = new FileReader();

                oFReader.readAsDataURL(document.getElementById("uploadImage").files[0]);

                oFReader.onload = function(oFREvent) {

                    var img = document.getElementById("uploadPreview");
                    //img.setAttribute("width", oFREvent.target.width);
                    //img.setAttribute("height", oFREvent.target.height);
                    img.src = oFREvent.target.result;

                    var fullPath = document.getElementById('uploadImage').value;
                    var fileName;
                    if (fullPath) {
                        var startIndex = (fullPath.indexOf('\\') >= 0 ? fullPath.lastIndexOf('\\') : fullPath.lastIndexOf('/'));
                        var filename = fullPath.substring(startIndex);
                        if (filename.indexOf('\\') === 0 || filename.indexOf('/') === 0) {
                            filename = filename.substring(1);
                        }

                        document.getElementById("imageSource").value = filename;
                    }
                }
            }
            function copyValueToHiddens()
            {
                console.log(document.getElementById('title').value);
                console.log(document.getElementById('description').value);
                console.log(document.getElementById('priority').value);
                console.log(document.getElementById('donationUrl').value);
                console.log(document.getElementById('endDate').value);
                console.log('Testing console');

                $('input[id="titleHidden"]').val(document.getElementById('title').value);
                $('input[id="descriptionHidden"]').val(document.getElementById('description').value);
                $('input[id="priorityHidden"]').val(document.getElementById('priority').value);
                $('input[id="donationUrlHidden"]').val(document.getElementById('donationUrl').value);
                $('input[id="endDateHidden"]').val(document.getElementById('endDate').value);


                var e = document.getElementById("categoryId");
                if (e.options[e.selectedIndex] != null) {
                    $('input[id="categoryHidden"]').val(e.options[e.selectedIndex].value);
                } else {
                    $('input[id="categoryHidden"]').val(0);
                }
                var e = document.getElementById("typeId");
                if (e.options[e.selectedIndex] != null) {
                    $('input[id="typeHidden"]').val(e.options[e.selectedIndex].value);
                } else {
                    $('input[id="typeHidden"]').val(0);
                }


            }
        </script>

        <title>Create donation</title>
    </head>

    <body>
        <br>
        <p class="logout">Welcome,&nbsp;&nbsp;<strong>${loggeduserName}</strong> &nbsp;</p>
        <p>&nbsp;</p>
        <p>&nbsp;</p>
        <a class="logoutBtn" href="logout">Logout</a>
        <div class="container">
            <nav>
                <ul class="nav">
                    <li><a href="donation.htm" title="Donation">Донации</a></li>
                    <li><a href="user.htm" title="User">Корисници</a></li>
                    <li><a href="type.htm" title="Type">Тип</a></li>
                    <li><a href="category.htm" title="Category">Категорија</a></li>
                    <li><a href="blogmanagement.htm" title="Blog">Блог</a></li>
                    <li><a href="aboutusadminpanel.htm" title="About">За Нас</a></li>
                </ul>
            </nav>
        </div>


        <div id="main">

            <div id="content">

                <table>
                    <form method="post" action="register" enctype="multipart/form-data" name="form">
                        <tr>  <td>Одбери Слика: 
                                <input type="submit" value="Прикачи" onclick="copyValueToHiddens()"></td>
                            <td><input type="file" id="uploadImage" name="file"  onchange="PreviewImage();" >
                                <img  id="uploadPreview" alt="No image available!" src="${imageLocation}" width="200px" height="200px" >
                                <input type="hidden" id="titleHidden" name="titleHidden" value="">
                                <input type="hidden" id="descriptionHidden" name="descriptionHidden" value="">
                                <input type="hidden" id="priorityHidden" name="priorityHidden" value="">
                                <input type="hidden" id="donationUrlHidden" name="donationUrlHidden" value="">
                                <input type="hidden" id="endDateHidden" name="endDateHidden" value="">
                                <input type="hidden" id="categoryHidden" name="categoryHidden" value="">
                                <input type="hidden" id="typeHidden" name="typeHidden" value="">

                            </td></tr>
                    </form>
                    <form:form commandName="createDonation" style="text-align:center; diplay:block;">
                        <tr><td>Наслов </td><td>   
                                <form:input path="title" required="required" id="title" value="${title}"/></td></tr>
                        <tr><td> Опис</td><td>
                                <form:input path="description" min="80" id="description" value="${description}" />
                            </td></tr>
                        <tr><td> Приоритет</td><td>
                                <form:input type="number" id="priority" path="priority" value="${priority}" /></td></tr>
                        <tr><td> Извор:</td><td> 
                                <form:input path="donationUrl" id="donationUrl" value="${donationUrl}"/></td></tr>
                        <tr><td><label>Важи до: </label></td><td>
                                <input type="text" id="endDate" name="endDate"  value="${endDate}" style="text-align: center"></td></tr>
                        <tr><td> Категорија: </td><td> <form:select path="category.id" itemValue="${selectedCategory}" id="categoryId" >
                                    <c:forEach var="cate" items="${allCategories}">
                                        <c:choose>
                                            <c:when test="${cate.id==selectedCategory}">
                                                <form:option value="${cate.id}" selected="selected">${cate.name}</form:option>
                                            </c:when>
                                            <c:otherwise>
                                                <form:option value="${cate.id}">${cate.name}</form:option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </form:select></td></tr> 
                        <tr><td>  Тип:  </td><td> <form:select path="type.id" itemValue="${selectedType}"  id="typeId" >
                                    <c:forEach var="tip" items="${allTypes}" >
                                        <c:choose>
                                            <c:when test="${tip.id==selectedType}">
                                                <form:option value="${tip.id}" selected="selected">${tip.name}</form:option>
                                            </c:when>
                                            <c:otherwise>
                                                <form:option value="${tip.id}">${tip.name}</form:option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </form:select></td></tr>
                        <tr><td>  Објавено:</td><td> <form:checkbox path="published" checked="checked"/></td></tr>

                        <form:hidden path="user.id" />
                        <form:hidden name="imageSource" id="imageSource" path="imageSource" value="${imageSource}"/>

                        <tr><td>  
                                Телефони: 
                            </td><td>  

                                <script type="text/javascript">

                $(document).ready(function() {

                    var counter = 2;

                    $("#addButton").click(function() {

                        if (counter > 5) {
                            alert("Само 5 телефони се дозволени");
                            return false;
                        }

                        var newTextBoxDiv = $(document.createElement('div'))
                                .attr("id", 'TextBoxDiv' + counter);

                        newTextBoxDiv.after().html('<label>Телефон ' + counter + ' : </label>' +
                                '<input type="text" name="phones" path="phones" id="textbox1" >');

                        newTextBoxDiv.appendTo("#TextBoxesGroup");

                        counter++;
                    });

                    $("#removeButton").click(function() {
                        if (counter == 1) {
                            alert("Нема повеќе телефони за бришење");
                            return false;
                        }

                        counter--;

                        $("#TextBoxDiv" + counter).remove();

                    });

                    $("#getButtonValue").click(function() {

                        var msg = '';
                        for (i = 1; i < counter; i++) {
                            msg += "\n Textbox " + i + " : " + $('#textbox' + i).val();
                        }
                        alert(msg);
                    });
                });
                                </script>

                                <div id='TextBoxesGroup'>
                                    <div id="TextBoxDiv1">
                                        <label>Телефон 1 : </label><input type="text" name="phones" path="phones" id="textbox1" >
                                    </div>
                                </div>
                                <input type='button' value='Add' id='addButton' >
                                <input type='button' value='Remove' id='removeButton'>
                                <input type='button' value='Get TextBox Value' id='getButtonValue'>

                            </td></tr>
                        <tr><td></td><td><input type="submit" value="Креирај" /></td></tr>

                        

                        <tr><td> Сметка:&nbsp;&nbsp; </td><td>   
                                <form method="POST" action="addAccount" >
                                    <input maxlength="15" name="account" /><input type="submit" value="Add!" />
                                </form></td></tr>

                    </form:form>


                </table>
            </div>            
        </div>
    </body>
</html>