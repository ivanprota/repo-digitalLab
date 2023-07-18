<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<html>

	<head>
	
		<meta charset="ISO-8859-1">
		<meta name="viewport" content="initial-scale=1, width=device-width">
		
		<link type="text/css" rel="stylesheet" href="<%= request.getContextPath()%>/css/footer.css">
		
	</head>
	
	<body>

		<!-- Inizio footer -->
		<footer id="footer">
			<div id="topFooter">
				<div id="leftTopFooter">
					<img src="<%=request.getContextPath()+"/imgs/logo.png"%>" alt="LOGO IMAGE">
					<p>
						Venditore hardware e costruttore di computer personalizzati
						ad alte prestazioni.<br>
						Personalizza il tuo PC per gaming, lavoro o altro.
					</p>
					<h1>Contattaci</h1>
					<p>00 00000000</p>
				</div>
				<div id="rightTopFooter">
					<div class="generalInfo">
						<ul>
							<li><a href="">Termini e condizioni</a></li>
							<li><a href="">Informativa sulla privacy</a></li>
							<li><a href="">Contatti</a></li>
							<li><a href="">Opzioni di pagamento</a></li>
						</ul>
					</div>
					<div class="generalInfo">
						<ul>
							<li><a href="">Modalità di reso</a></li>
							<li><a href="">Garanzia</a></li>
							<li><a href="">Domande frequenti</a></li>
							<li><a href="">Testimonianze</a></li>
						</ul>
					</div>
					<div class="generalInfo">
						<ul>
							<li><a href="">Informazioni sulla consegna</a></li>
							<li><a href="">I nostri servizi</a></li>
							<li><a href="<%=request.getContextPath()%>/common/support.jsp">Assistenza</a></li>
							<li><a href="">Opportunità di lavoro</a></li>
						</ul>
					</div>					
				</div>
			</div>
			
			<div id="bottomFooter">
				<div id="leftBottomFooter">
					<p>
						&copy; 2023 Digital Lab
					</p>
				</div>
				<div id="rightBottomFooter">
					<div class="socialFooter">
						<a href="" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
							<img src="<%=request.getContextPath()+"/imgs/facebook.png"%>" alt="FACEBOOK LOGO">
						</a>
					</div>
					<div class="socialFooter">
						<a href="" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
							<img src="<%=request.getContextPath()+"/imgs/instagram.png"%>" alt="INSTAGRAM LOGO">
						</a>
					</div>
					<div class="socialFooter">
						<a href="" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
							<img src="<%=request.getContextPath()+"/imgs/tiktok.png"%>" alt="TIKTOK LOGO">
						</a>
					</div>					
					<div class="socialFooter">
						<a href="" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
							<img src="<%=request.getContextPath()+"/imgs/twitter.png"%>" alt="TWITTER LOGO">
						</a>
					</div>
					<div class="socialFooter">
						<a href="" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
							<img src="<%=request.getContextPath()+"/imgs/linkedin.png"%>" alt="LINKEDIN LOGO">
						</a>
					</div>
					<div class="socialFooter">
						<a href="" onmouseover="borderItem(this)" onmouseout="deleteBorderItem(this)">
							<img src="<%=request.getContextPath()+"/imgs/youtube.png"%>" alt="YOUTUBE LOGO">
						</a>
					</div>
				</div>
			</div>
		</footer>
		<!-- Fine footer -->

	</body>

</html>