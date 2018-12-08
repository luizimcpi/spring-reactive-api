package com.devlhse.springreactiveapi.utils;

import com.devlhse.springreactiveapi.dto.request.OwnerRequest;
import com.devlhse.springreactiveapi.dto.response.OwnerResponse;
import com.devlhse.springreactiveapi.model.Owner;

public class OwnerUtils {

    public static OwnerResponse toResponse(Owner owner) {
        return OwnerResponse.OwnerResponseBuilder.anOwnerResponse()
                .id(owner.getId())
                .name(owner.getName())
                .documentNumber(owner.getDocumentNumber())
                .createdAt(owner.getCreatedAt())
                .updatedAt(owner.getUpdatedAt())
                .build();
    }

    public static Owner toModel(OwnerRequest ownerRequest){
        Owner owner = new Owner();
        owner.setName(ownerRequest.getName());
        owner.setDocumentNumber(ownerRequest.getDocumentNumber());
        return owner;
    }
}
