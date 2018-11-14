package br.com.digicom.modelo;

import com.strongloop.android.loopback.Model;

public class PalavraChaveEstatistica extends Model{

	private String DataConsulta;
	private Double MediaCpc;
	private Long VolumePesquisa;
	private Double IndiceCompeticao;
	
	private String palavraChaveRaizId;
	private String palavraChaveGoogleId;
	
	
	public String getDataConsulta() {
		return DataConsulta;
	}
	public void setDataConsulta(String dataConsulta) {
		DataConsulta = dataConsulta;
	}
	public Double getMediaCpc() {
		return MediaCpc;
	}
	public void setMediaCpc(Double mediaCpc) {
		MediaCpc = mediaCpc;
	}
	public Long getVolumePesquisa() {
		return VolumePesquisa;
	}
	public void setVolumePesquisa(Long volumePesquisa) {
		VolumePesquisa = volumePesquisa;
	}
	public Double getIndiceCompeticao() {
		return IndiceCompeticao;
	}
	public void setIndiceCompeticao(Double indiceCompeticao) {
		IndiceCompeticao = indiceCompeticao;
	}
	public String getPalavraChaveRaizId() {
		return palavraChaveRaizId;
	}
	public void setPalavraChaveRaizId(String palavraChaveRaizId) {
		this.palavraChaveRaizId = palavraChaveRaizId;
	}
	public String getPalavraChaveGoogleId() {
		return palavraChaveGoogleId;
	}
	public void setPalavraChaveGoogleId(String palavraChaveGoogleId) {
		this.palavraChaveGoogleId = palavraChaveGoogleId;
	}
	
	
	
	
	
}
