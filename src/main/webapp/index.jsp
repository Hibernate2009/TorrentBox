<%@ taglib uri="http://java.sun.com/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
<title>Bootstrap 101 Template</title>

<!-- Bootstrap -->
<link href="css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="css/sticky-footer.css" rel="stylesheet">

<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>

	<div class="container">

		<div class="page-header">
			<h1>Torrent box</h1>
		</div>
		
		<div class="bs-example" style="padding-bottom: 24px;">
			<form name="uploadForm" id="uploadForm1" action="RefreshTorrentServlet" method="post">
    			<button type="submit" class="btn btn-primary" data-toggle="modal" data-target="#myModal">Refresh</button>
    		</form>
  		</div>
		
		
		<c:if test="${torrentModel != null}">

			<table class="table table-bordered">
				<c:forEach var="torrent" items="${torrentModel.torrents}">
				
					
					<tr class="<c:out value="${torrent.status}" />">
						<td width="50%"><c:out value="${torrent.fileName}" /></td>
						<td width="40%">
							<div class="progress" style="margin-bottom: 0px;">
								<div class="progress-bar" role="progressbar" style="min-width: 2em;" aria-valuenow="<c:out value="${torrent.progress}" />" aria-valuemin="0" aria-valuemax="100" style="width: <c:out value="${torrent.progress}" />%;"><c:out value="${torrent.progress}" />%</div>
							</div>
						</td>
						<td width="10%">
							<form action="DeleteTorrentServlet" method="post">
								<input type="hidden" name="torrentId" value="<c:out value="${torrent.torrentId}" />" />
								<button type="submit" class="btn btn-default btn-xs">delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>

		

	</div>

	<footer class="footer">
		<div class="container">
			<div class="row">
			<form name="uploadForm" id="uploadForm1" enctype="multipart/form-data" action="UploadTorrentServlet" method="post">
				<div class="col-lg-12">
					<div class="input-group">
						<input type="hidden" name="hiddenfield1" value="ok">
						<input type="file" class="form-control" name="file">
						 <span class="input-group-btn"> 
						 	<input type="submit" class="btn btn-default" value="Go!" />
						</span>
					</div>
				</div>
			</form>
		</div>
		</div>
	</footer>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
	<!-- Include all compiled plugins (below), or include individual files as needed -->
	<script src="js/bootstrap.min.js"></script>
</body>
</html>