package com.lucas.projeto.projeto.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lucas.projeto.projeto.domain.Cliente;
import com.lucas.projeto.projeto.domain.enums.TipoCliente;
import com.lucas.projeto.projeto.dto.ClienteNewDTO;
import com.lucas.projeto.projeto.repositories.ClienteRepository;
import com.lucas.projeto.projeto.resources.exception.FieldMessage;
import com.lucas.projeto.projeto.services.validation.utils.BR;

import org.springframework.beans.factory.annotation.Autowired;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNewDTO> {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public void initialize(ClienteInsert ann) {
    }

    @Override
    public boolean isValid(ClienteNewDTO objDTO, ConstraintValidatorContext context) {
        List<FieldMessage> list = new ArrayList<>();

        if (objDTO.getTipo().equals(TipoCliente.PESSOAFISICA.getCodigo()) && !BR.isValidSsn(objDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CPF inválido"));
        }

        if (objDTO.getTipo().equals(TipoCliente.PESSOAJURIDICA.getCodigo()) && !BR.isValidTfn(objDTO.getCpfOuCnpj())) {
            list.add(new FieldMessage("cpfOuCnpj", "CNPJ inválido"));
        }

        Cliente aux = clienteRepository.findByEmail(objDTO.getEmail());
        if (aux != null) {
            list.add(new FieldMessage("email", "Email já existente"));
        }

        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getFieldName())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
