<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    
    <h1 class="h3 mb-2 text-gray-800">Material </h1>
    <p class="mb-4">Formulario de Cadastro</p>
    
    <a class="btn btn-secondary mb-4" id="voltarmaterial" href="${pageContext.request.contextPath}/MaterialListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
        <div class="row">
            
        
    <div class="col-lg-9">
        <div class="card shadow mb-4">
            <div class="card-body">
                <div class="form-group">
                    <label>ID</label>
                    <input class="form-control" type="´text" name="idMaterialHospitalar" id="idMaterialHospitalar"
                           value="${material.idMaterialHospitalar}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>Nome Material</label>
                    <input class="form-control" type="text" name="nomeMaterial" id="nomeMaterial"
                           value="${material.nomeMaterial}" size="100" maxlength="100"/>
                </div>
                <div class="form-group">
                    <label>Quantidade</label>
                    <input class="form-control" type="text" name="qtde" id="qtde"
                           value="${material.qtde}" size="100" maxlength="100"/>
                </div>
                <div class="form-group">
                <label>Categoria : </label>
                    <select name="categoria" id="categoria">
                        <option value="">Selecione</option>
                        <c:forEach var="categoria" items="${categorias}">
                            <option value="${categoria.idCategoria}"
                                    ${material.categoria.idCategoria == categoria.idCategoria ? "selected":""}> ${categoria.nomeCategoria}
                            </option>
                        </c:forEach>
                    </select>
                </div>
                        <div class="form-group">
                            <button class ="btn btn-success" type="submit" id="submit" onclick="validarCampos()">
                                Salvar Documento
                            </button>
                        </div>
            </div>
        </div>
    </div>
</div>
</div>
                        <style type="text/css">
    .inputfile {
        /* visibility: hidden etc. wont work */
        width: 0.1px;
        height: 0.1px;
        opacity: 0;
        overflow: hidden;
        position: absolute;
        z-index: -1;    }
    .inputfile:focus + label {
        /* keyboard navigation */
        outline: 1px dotted #000;
        outline: -webkit-focus-ring-color auto 5px;    }
    .inputfile + label * {
        pointer-events: none;    }
    .borda{
        position: relative;
        margin: 0 20px 30px 0;
        padding: 10px;
        border: 1px solid #e1e1e1;
        border-radius: 3px;
        background: #fff;
        -webkit-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        -moz-box-shadow: 0px 0px 3px rgba(0,0,0,0.06);
        box-shadow: 0px 0px 3px rgba(0,0,0,0.06);    }
</style>

<script>
    
    function validarCampos() {
        console.log("entrei na validação de campos");
        if (document.getElementById("nomeMaterial").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o nome  do material!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomeMaterial").focus();
        } else if (document.getElementById("qtde").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a quantidade!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#qtde").focus();
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        
        $.ajax({
            type: 'post',
            url: 'MaterialCadastrar',
            data: {
                idMaterialHospitalar: $('#idMaterialHospitalar').val(),
                nomeMaterial: $('#nomeMaterial').val(),
                qtde: $('#qtde').val(),
                categoria: $('#categoria').val(),
                
            },
            success:
                    function (data) {
                        console.log("reposta servlet->");
                        console.log(data);
                        if (data == 1) {
                            Swal.fire({
                                position: 'center',
                                icon: 'success',
                                title: 'Sucesso',
                                text: 'Material gravada com sucesso!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar material!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        }
                        window.location.href = "${pageContext.request.contextPath}/MaterialListar";
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/MaterialListar";
                    }
        });
    }
    
    
</script>   
<jsp:include page="/footer.jsp"/>