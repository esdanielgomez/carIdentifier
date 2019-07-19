<!DOCTYPE html>
<html lang="en">
<meta charset="UTF-8">
<head>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <meta name="description" content="">
  <meta name="author" content="">
  
  <title>App Automóvil</title>
  
  <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
  <link href="vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">
  <link href="css/sb-admin.css" rel="stylesheet">
</head>

<body class="" id="">
  <!-- Navigation-->
  <nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top" id="mainNav"><a class="navbar-brand" href="index.jsp">App Automóvil</a>
    <button class="navbar-toggler navbar-toggler-right" type="button" data-toggle="collapse" data-target="#navbarResponsive" aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    
    
  </nav>
  <!-- Contenido de la pagina -->
  <div class="content">
    <div class="container-fluid">
      <ol class="breadcrumb">
       <!-- Navegador 1 de la pagina -->
        <li class="breadcrumb-item">
          <a href="index.jsp">Dashboard</a>
        </li>
        <!-- Navegador 2 de la pagina en el contenido -->
        <li class="breadcrumb-item active">Página de inicio</li>
      </ol>
      <div class="row">
        <div class="col-12">
         <!-- titulo de la pagina -->
         <p>	Esta es la página principal que nos permite visualizar la información final.</p>
         
         <%@ page import="Servicio.OpenALPR"%> 
         <%
            OpenALPR ln = new OpenALPR();
            ln.realizarConsulta();
         %>
         <p>
         <h4>Imagen detectada</h4>
         <p>
         <img src="<%=ln.rutaImagen%>" width="30%" >
         
         <p>
         
         <h4>Información de la imagen</h4>
         <p>
         <table class="table">
            <tbody>
              <tr>
                <th scope="row">Placa</th>
                <td><%= ln.placa %></td>
              </tr>
              <tr>
                <th scope="row">Tipo de auto: </th>
                <td><%= ln.tipoAuto %></td>
              </tr>
              <tr>
                <th scope="Marca">Marca del auto: </th>
                <td><%= ln.marcaAuto %></td>
              </tr>
              <tr>
                <th scope="Color">Color:</th>
                <td><%= ln.colorAuto %></td>
              </tr>
              <tr>
                <th scope="Año">Año del auto: </th>
              <td><%= ln.anioAuto %></td>
              </tr>
            </tbody>
          </table>
         
        </div>
      </div>
    </div>
    <!-- Fin del contenido -->
    <!-- Inicio del pie de pagina -->
    
    <!-- Scroll to Top Button-->
    <a class="scroll-to-top rounded" href="#page-top">
      <i class="fa fa-angle-up"></i>
    </a>
    
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="vendor/jquery-easing/jquery.easing.min.js"></script>
    <!-- Custom scripts for all pages-->
    <script src="js/sb-admin.min.js"></script>
  </div>
</body>

</html>
