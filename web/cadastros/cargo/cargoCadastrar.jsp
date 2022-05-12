<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    
    <h1 class="h3 mb-2 text-gray-800" id="voltar">Cargo</h1>
    <p class="mb-4" id="voltar">Formulario de Cadastro</p>
    
    <a class="btn btn-secondary mb-4" id="voltar" href="${pageContext.request.contextPath}/CargoListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
        
    <div class="col-lg-9">
        <div class="card shadow mb-4">
            <div class="card-body">
                <div class="form-group">
                    <label>ID</label>
                    <input class="form-control" type="�text" name="idcargo" id="idcargo"
                           value="${cargo.idcargo}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>Nome Cargo</label>
                    <input class="form-control" type="text" name="nomecargo" id="nomecargo"
                           value="${cargo.nomecargo}" size="100" maxlength="100"/>
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
        console.log("entrei na valida��o de campos");
        if (document.getElementById("nomecargo").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a nome de cargo!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomecargo").focus();
        
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        
        $.ajax({
            type: 'post',
            url: 'CargoCadastrar',
            data: {
                idcargo: $('#idcargo').val(),
                nomecargo: $('#nomecargo').val(),
                
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
                                text: 'Cargo gravado com sucesso!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'N�o foi poss�vel gravar a despesa!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        }
                        window.location.href = "${pageContext.request.contextPath}/CargoListar";
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/CargoListar";
                    }
        });
    }
    
   
    
</script>   
<jsp:include page="/footer.jsp"/>