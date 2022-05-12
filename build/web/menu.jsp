<body id="bg-remedy">
<div class="wrapper d-flex align-items-stretch">
    <nav id="sidebar" class="active">
        <h1><a href="index.jsp" class="logo">SiMed</a></h1>
        <ul class="list-unstyled components mb-5">
            <li class="active">
                <a href="index.jsp""><span class="fa fa-home"></span> Home</a>
            </li>
            <li>
                <a href="${pageContext.request.contextPath}/CargoListar"><span class="fa fa-user-md"></span> Cargo</a>

            </li>
            <li>
                <a href="${pageContext.request.contextPath}/FuncionarioListar"><span class="fa fa-user"></span> Funcionário</a>

            </li>

            <li>
                <a href="${pageContext.request.contextPath}/CategoriaListar"><span class="fa fa-plus-square"></span> Categoria</a>

            </li>

            <li>
                <a href="${pageContext.request.contextPath}/MaterialListar"><span class="fa fa-medkit"></span> Material Hospitalar</a>

            </li>

            <li>
                <a href="${pageContext.request.contextPath}/UnidadeListar"><span class="fa fa-hospital-o"></span> Unidade Hospitalar</a>

            </li>
        </ul>
    </nav>

    <!-- Page Content  -->
    <div id="content" class="p-4 p-md-5">
            <div class="container-fluidbutton">

                <button type="button" id="sidebarCollapse" class="btn btn-primary">
                    <i class="fa fa-bars"></i>
                    <span class="sr-only"></span>
                </button>
            </div>
    </div>
</div>
<!--<script src="js/jquery.min.js"></script>-->
<script src="js/popper.js"></script>
<script src="js/bootstrap.min.js"></script>
<script src="js/main.js"></script>
