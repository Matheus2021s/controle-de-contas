package br.com.mariah.controledecontas.resource;

import br.com.mariah.controledecontas.domain.*;
import br.com.mariah.controledecontas.dto.*;
import br.com.mariah.controledecontas.genericcrud.resources.Resource;
import br.com.mariah.controledecontas.genericcrud.resources.ResourceItem;
import br.com.mariah.controledecontas.persistence.*;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class GenericCrudResources extends Resource {

    protected GenericCrudResources() {
        setResources(resourceMap());
    }

    private HashMap<String, ResourceItem> resourceMap() {
        HashMap<String, ResourceItem> map = new HashMap<>();
        map.put("banco", new ResourceItem(Banco.class, BancoDTO.class, BancoPersistence.class));
        map.put("salario", new ResourceItem(Salario.class, SalarioDTO.class, SalarioPersistence.class));
        map.put("orcamento", new ResourceItem(Orcamento.class, OrcamentoDTO.class, OrcamentoPersistence.class));
        map.put("cartao", new ResourceItem(Cartao.class, CartaoDTO.class, CartaoPersistence.class));
        map.put("emprestimo", new ResourceItem(Emprestimo.class, EmprestimoDTO.class, EmprestimoPersistence.class));
        return map;
    }
}
