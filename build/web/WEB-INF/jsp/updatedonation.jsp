<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!DOCTYPE html>
<html>
    <head>

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
    <br>
    <link rel="stylesheet" href="<c:url value="/resources/css/style_horizontal_panel.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/admin_style.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-1.9.2.css"/>" />
    <link rel="stylesheet" href="<c:url value="/resources/css/jquery-ui-timepicker.css"/>" />
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.8.3.min.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-1.9.2.js"/>"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jquery-ui-timepicker.js"/>"></script>

    <script type="text/javascript">
        $(function() {
            $("input[name='endDate']").datepicker({dateFormat: "yy-mm-dd", changeYear: true, changeMonth: true});
            $("input[name='startDate']").datepicker({dateFormat: "yy-mm-dd", maxDate: 0, changeYear: true, changeMonth: true});
        });
    </script>


    <title>Update Donation Page</title>

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

</head>
<body>
    <p class="logout">Welcome,&nbsp;&nbsp;<strong>${loggeduserName}</strong> &nbsp;</p>
    <p>&nbsp;</p>
    <p>&nbsp;</p>
    <a class="logoutBtn" href="logout">Logout</a>
    <div class="container">
        <nav>
            <ul class="nav">

                <li><a href="donation.htm" title="Donation">Donation</a></li>
                <li><a href="user.htm" title="User">User</a></li>
                <li><a href="type.htm" title="Type">Type</a></li>
                <li><a href="category.htm" title="Category">Category</a></li>
                <li><a href="blogmanagement.htm" title="Blog">Blog</a></li>

            </ul>
        </nav>
    </div>

    <div id="content">
        <table>
            <form method="post" action="FileUploadServlet" enctype="multipart/form-data" name="form">

                <tr>  <td>Select image to upload: 
                        <input type="submit" value="Upload"  onclick="copyValueToHiddens()"></td>
                    <td><input type="file" id="uploadImage" name="file"  onchange="PreviewImage();" >
                        <img  id="uploadPreview" alt="No image available!" src="/data/images/${currentImageSource}" width="200px" height="200px" >

                        <input type="hidden" id="titleHidden" name="titleHidden" value="">
                        <input type="hidden" id="descriptionHidden" name="descriptionHidden" value="">
                        <input type="hidden" id="priorityHidden" name="priorityHidden" value="">
                        <input type="hidden" id="donationUrlHidden" name="donationUrlHidden" value="">
                        <input type="hidden" id="endDateHidden" name="endDateHidden" value="">
                        <input type="hidden" id="categoryHidden" name="categoryHidden" value="">
                        <input type="hidden" id="typeHidden" name="typeHidden" value="">
                        
                    </td></tr>
            </form>
            <form:form commandName="updateDonationForm">
                <c:choose>
                    <c:when test="${empty param}">
                        ID: <form:input path="id" required="required" /><br>
                    </c:when>
                    <c:otherwise>
                        <form:hidden path="id" value="${idToUpdate}" /><br>
                    </c:otherwise>
                </c:choose>
                <tr><td>Title: </td><td><form:input path="title" required="required" id="title"  maxlength="20" value="${currentTitle}"/></td></tr> 
                <tr><td> Donation Url:</td><td><form:input path="donationUrl" id="donationUrl" maxlength="20" value="${currentDonationUrl}"/></td></tr> 
                <tr><td> Description:</td><td><form:input path="description" id="description" maxlength="20" value="${currentDescription}"/></td></tr> 
                <tr><td>  <label>End date: </label></td><td>
                        <input type="text" id="endDate" name="endDate"  id="endDate" value="${currentEndDate}" autocomplete="off" style="text-align: center" maxlength="255" ></td></tr>                           

                <tr><td> Priority:</td><td>  <form:input type="number" id="priority" path="priority" name="name" value="${currentPriority}" /></td></tr> 
                <tr><td> Category: </td><td><form:select path="category.id" id="categoryId" itemValue="${currentCategoryId}" itemLabel="${currentCategoryName}" >
                            <c:forEach var="cate" items="${allCategories}">
                                <c:choose>
                                    <c:when test="${cate.id==currentCategoryId}">
                                        <form:option value="${cate.id}" selected="selected">${cate.name}</form:option>
                                    </c:when>
                                    <c:otherwise>
                                        <form:option value="${cate.id}">${cate.name}</form:option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </form:select></td></tr> 
                <tr><td>  Type:  </td><td><form:select path="type.id"  id="typeId"  itemValue="${currentTypeId}"  itemLabel="${currentTypeName}" >
                            <c:forEach var="tip" items="${allTypes}" >
                                <c:choose>
                                    <c:when test="${tip.id==currentTypeId}">
                                        <form:option value="${tip.id}" selected="selected">${tip.name}</form:option>
                                    </c:when>
                                    <c:otherwise>
                                        <form:option value="${tip.id}">${tip.name}</form:option>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </form:select></td></tr> 
                <tr><td>  Published:</td><td> <form:checkbox path="published" checked="${currentPublished}" /> </td></tr> 
                <tr><td>  Update</td><td> <input type="submit" value="Update Donation!" /></td></tr> 
                <tr><td> Phones: </td><td>  <form:input path="phones" name="phone" maxlength="15" value="${donationPhone}"/></td></tr> 
                <tr><td>  Account:</td><td> <form:input path="accounts" name="account" maxlength="15" value="${donationAccount}"/></td></tr> 

                <form:hidden path="imageSource" name="imageSource" id="imageSource" value="${currentImageSource}" />
                <form:hidden path="user.id" value="${currentUserId}"/><br>


            </form:form>
            <p id="redLabel">${redLabel}</p>
            <p id="greenLabel">${greenLabel}</p>
    </div>
</body>
</html>