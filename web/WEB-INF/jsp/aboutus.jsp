<!DOCTYPE html>
<html>
    <head>
        <title>jQuery add / remove textbox example</title>

        <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <script type="text/javascript" src="<c:url value="/resources/js/jquery-1.9.1.min.js"/>"></script>

        <style type="text/css">
            div{
                padding:8px;
            }
        </style>

    </head>

    <body>

        <script type="text/javascript">

            $(document).ready(function() {

                var counter = 2;

                $("#addButton").click(function() {

                    if (counter > 5) {
                        alert("Only 5 phones allowed");
                        return false;
                    }

                    var newTextBoxDiv = $(document.createElement('div'))
                            .attr("id", 'TextBoxDiv' + counter);

                    newTextBoxDiv.after().html('<label>Phone ' + counter + ' : </label>' +
                            '<input type="tel" name="phones" id="textbox' + counter + '" value="" >');

                    newTextBoxDiv.appendTo("#TextBoxesGroup");


                    counter++;
                });

                $("#removeButton").click(function() {
                    if (counter == 1) {
                        alert("No more phones to remove");
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
            <form method="POST" action="aboutus">
                <div id="TextBoxDiv1">
                    <label>Phone 1 : </label><input type="tel" id='textbox1' name="phones" >
                </div>
        </div>
        <input type='button' value='Add' id='addButton' >
        <input type='button' value='Remove' id='removeButton'>
        <input type='button' value='Get TextBox Value' id='getButtonValue'>
        <input type="button" value="submit"/>
        </form>
</body>
</html>