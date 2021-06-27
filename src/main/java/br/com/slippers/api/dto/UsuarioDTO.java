package br.com.slippers.api.dto;

import java.util.List;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private int totalCompras;
    private List<NotaChineloClienteDTO> notaChineloClientesDTO;
    private List<PedidoDTO> pedidosDTO;
    private List<CartaoDTO> cartoesDTO;
    private CarrinhoDTO carrinhoDTO;

    public UsuarioDTO(Long id,
    String nome,
    String email,
    int totalCompras,
    List<NotaChineloClienteDTO> notaChineloClienteDTO,
    List<PedidoDTO> pedidosDTO,
    List<CartaoDTO> cartoesDTO,
    CarrinhoDTO carrinhoDTO) {
    this.id = id;
    this.nome = nome;
    this.email = email;
    this.totalCompras = totalCompras;
    this.notaChineloClientesDTO = notaChineloClienteDTO;
    this.pedidosDTO = pedidosDTO;
    this.cartoesDTO = cartoesDTO;
    this.carrinhoDTO = carrinhoDTO;
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTotalCompras() {
        return this.totalCompras;
    }

    public void setTotalCompras(int totalCompras) {
        this.totalCompras = totalCompras;
    }

    public List<NotaChineloClienteDTO> getNotaChineloClientesDTO() {
        return this.notaChineloClientesDTO;
    }

    public void setNotaChineloClientesDTO(List<NotaChineloClienteDTO> notaChineloClientesDTO) {
        this.notaChineloClientesDTO = notaChineloClientesDTO;
    }

    public List<PedidoDTO> getPedidosDTO() {
        return this.pedidosDTO;
    }

    public void setPedidosDTO(List<PedidoDTO> pedidosDTO) {
        this.pedidosDTO = pedidosDTO;
    }

    public List<CartaoDTO> getCartoesDTO() {
        return this.cartoesDTO;
    }

    public void setCartoesDTO(List<CartaoDTO> cartoesDTO) {
        this.cartoesDTO = cartoesDTO;
    }

    public CarrinhoDTO getCarrinhoDTO() {
        return this.carrinhoDTO;
    }

    public void setCarrinhoDTO(CarrinhoDTO carrinhoDTO) {
        this.carrinhoDTO = carrinhoDTO;
    }
}
