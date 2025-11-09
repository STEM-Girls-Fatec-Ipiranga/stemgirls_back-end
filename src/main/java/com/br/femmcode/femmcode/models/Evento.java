package main.java.com.br.femmcode.femmcode.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Document(collection = "eventos")
public class Evento {
    
    @Id
    private String id;
    
    @NotBlank(message = "Nome da empresa é obrigatório")
    private String nomeEmpresa;
    
    @NotBlank(message = "Nome do evento é obrigatório")
    private String nomeEvento;
    
    @NotBlank(message = "Data do evento é obrigatório")
    private String dataEvento;
    
    @NotBlank(message = "Hora do evento é obrigatório")
    private String horaEvento;
    
    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;
    
    private Localidade localidade;
    private String linkEvento;
    
    @NotBlank(message = "Descrição é obrigatório")
    private String descricao;
    
    @NotBlank(message = "Foto de divulgação é obrigatório")
    private String fotoDivulgacao;

    // Getters e Setters
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    
    public String getNomeEvento() { return nomeEvento; }
    public void setNomeEvento(String nomeEvento) { this.nomeEvento = nomeEvento; }
    
    public String getDataEvento() { return dataEvento; }
    public void setDataEvento(String dataEvento) { this.dataEvento = dataEvento; }
    
    public String getHoraEvento() { return horaEvento; }
    public void setHoraEvento(String horaEvento) { this.horaEvento = horaEvento; }
    
    public String getModelo() { return modelo; }
    public void setModelo(String modelo) { this.modelo = modelo; }
    
    public Localidade getLocalidade() { return localidade; }
    public void setLocalidade(Localidade localidade) { this.localidade = localidade; }
    
    public String getLinkEvento() { return linkEvento; }
    public void setLinkEvento(String linkEvento) { this.linkEvento = linkEvento; }
    
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    
    public String getFotoDivulgacao() { return fotoDivulgacao; }
    public void setFotoDivulgacao(String fotoDivulgacao) { this.fotoDivulgacao = fotoDivulgacao; }

    public static class Localidade {
        private String cidade;
        private String estado;
        private String bairro;
        private String rua;
        private String cep;
        private String numero;
        private String complemento;

        // Getters e Setters
        public String getCidade() { return cidade; }
        public void setCidade(String cidade) { this.cidade = cidade; }
        
        public String getEstado() { return estado; }
        public void setEstado(String estado) { this.estado = estado; }
        
        public String getBairro() { return bairro; }
        public void setBairro(String bairro) { this.bairro = bairro; }
        
        public String getRua() { return rua; }
        public void setRua(String rua) { this.rua = rua; }
        
        public String getCep() { return cep; }
        public void setCep(String cep) { this.cep = cep; }
        
        public String getNumero() { return numero; }
        public void setNumero(String numero) { this.numero = numero; }
        
        public String getComplemento() { return complemento; }
        public void setComplemento(String complemento) { this.complemento = complemento; }
    }
}