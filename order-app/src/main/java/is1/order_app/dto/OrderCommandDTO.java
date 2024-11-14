package is1.order_app.dto;

import lombok.Data;

@Data
public class OrderCommandDTO {
    private String commandName;

    public OrderCommandDTO() {}

    public OrderCommandDTO(String commandName) {
        this.commandName = commandName;
    }

    public String getCommandName() {
        return commandName;
    }
}

