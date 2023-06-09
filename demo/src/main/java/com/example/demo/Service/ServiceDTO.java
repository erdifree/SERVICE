package com.example.demo.Service;

/**
 * Questa interfaccia firma i metodi dei Service. Osservare che, a differenza di
 * AbstactService, dipende solo dal parametro DTO.
 * @param <DTO>
 * @see AbstractService
 */
public interface ServiceDTO<DTO> {

	public Iterable<DTO> getAll();

	public DTO read(Integer id);

	public DTO insert (DTO dto);

	public DTO update (DTO dto);

	public void delete (Integer id);
}

