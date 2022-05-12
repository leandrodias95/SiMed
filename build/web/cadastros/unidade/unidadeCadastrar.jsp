<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<jsp:include page="/header.jsp"/>
<jsp:include page="/menu.jsp"/>

<div class="container-fluid">
    
    <h1 class="h3 mb-2 text-gray-800" id="voltar">Unidade Hospitalar</h1>
    <p class="mb-4" id="voltar">Formulario de Cadastro</p>
    
    <a class="btn btn-secondary mb-4" id="voltar" href="${pageContext.request.contextPath}/UnidadeListar">
        <i class="fas fa-undo-alt"></i>
        <strong>Voltar</strong>
    </a>
        
    <div class="col-lg-9">
        <div class="card shadow mb-4">
            <div class="card-body">
                <div class="form-group">
                    <label>ID</label>
                    <input class="form-control" type="´text" name="idunidadeHospitalar" id="idunidadeHospitalar"
                           value="${unidade.idunidadeHospitalar}" readonly="readonly"/>
                </div>
                <div class="form-group">
                    <label>Nome da Unidade Hospitalar</label>
                    <input class="form-control" type="text" name="nomeUnidade" id="nomeUnidade"
                           value="${unidade.nomeUnidade}" size="100" maxlength="100"/>
                </div>
                
                <div class="form-group">
                    <label>CNPJ</label>
                    <input class="form-control" type="text" name="cnpj" id="cnpj"
                           value="${unidade.cnpj}" size="100" maxlength="100"/>
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
        console.log("entrei na validação de campos");
        if (document.getElementById("nomeUnidade").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique a nome da Unidade Hospitalar!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#nomeUnidade").focus();
            
        } else if (document.getElementById("cnpj").value == '') {
            Swal.fire({
                position: 'center',
                icon: 'error',
                title: 'Verifique o cnpj da Unidade Hospitalar!',
                showConfirmButton: false,
                timer: 1000
            });
            $("#cnpj").focus();
        
        } else {
            gravarDados();
        }
    }
    
    function gravarDados() {
        console.log("Gravando dados ....");
        
        $.ajax({
            type: 'post',
            url: 'UnidadeCadastrar',
            data: {
                idunidadeHospitalar: $('#idunidadeHospitalar').val(),
                nomeUnidade: $('#nomeUnidade').val(),
                cnpj: $('#cnpj').val(),
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
                                text: 'Unidade Hospitalar gravado com sucesso!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        } else {
                            Swal.fire({
                                position: 'center',
                                icon: 'error',
                                title: 'Erro',
                                text: 'Não foi possível gravar a Unidade Hospitalar!',
                                showConfirmButton: false,
                                timer: 1000
                            })
                        }
                        window.location.href = "${pageContext.request.contextPath}/UnidadeListar";
                    },
            error:
                    function (data) {
                        window.location.href = "${pageContext.request.contextPath}/UnidadeListar";
                    }
        });
    }
    
   
    
</script>   
<jsp:include page="/footer.jsp"/>