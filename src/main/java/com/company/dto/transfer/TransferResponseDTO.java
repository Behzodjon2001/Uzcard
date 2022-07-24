package com.company.dto.transfer;

import lombok.Data;

@Data
public class TransferResponseDTO {
    private String status;
    private String message;
    private TransferDTO transfer;

    public TransferResponseDTO(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public TransferResponseDTO(String status, String message, TransferDTO transfer) {
        this.status = status;
        this.message = message;
        this.transfer = transfer;
    }
}
