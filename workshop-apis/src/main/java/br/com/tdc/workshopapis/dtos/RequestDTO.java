package br.com.tdc.workshopapis.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RequestDTO {

	private Long page = 0L;
	private Long size = 5L;

	@Override
	public String toString() {
		return "RequestDTO [page=" + page + ", size=" + size + "]";
	}

}
