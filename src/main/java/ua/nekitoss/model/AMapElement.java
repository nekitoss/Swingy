package ua.nekitoss.model;

import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Data
public abstract class AMapElement {
  @NotEmpty
  protected String name = "";
  protected int xPos = 0;
  protected int yPos = 0;
  @NotEmpty
  protected char mapSign;
}
