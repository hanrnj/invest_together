package bit.it.into.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ValidMemberDTO {
   
   @NotEmpty(message="�ٽ� �Է��� �ּ���.")
   @Size(min=4, max=12, message="�ٽ� �Է��� �ּ���.")
   @Pattern(regexp = "^[a-z0-9]+$", message="�ٽ� �Է��� �ּ���.")
   private String id;
   
   @NotEmpty(message = "�ٽ� �Է��� �ּ���.")
   @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{8,32}", message="�ٽ� �Է��� �ּ���.")
   private String pw;
   
   @NotEmpty(message = "�ٽ� �Է��� �ּ���.")
   @Size(min=2, max=8, message="�ٽ� �Է��� �ּ���.")
   @Pattern(regexp = "^[��-�R]+$", message="�ٽ� �Է��� �ּ���.")
   private String name;
   
   @NotEmpty(message = "�ٽ� �Է��� �ּ���.")
   @Size(min=2, max=8, message="�ٽ� �Է��� �ּ���.")
   @Pattern(regexp = "^[��-�Ra-zA-Z0-9]+$", message="�ٽ� �Է��� �ּ���.")
   private String nickname;
   
   @NotEmpty(message = "�ٽ� �Է��� �ּ���.")
   @Email(message = "�ٽ� �Է��� �ּ���.")
   private String email;
   
   @NotEmpty(message = "�ٽ� �Է��� �ּ���.")
   @Pattern(regexp="^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", message = "�ٽ� �Է��� �ּ���.")
   private String phone;
}
