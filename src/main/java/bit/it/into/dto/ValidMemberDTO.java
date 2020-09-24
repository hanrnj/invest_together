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
   
   @NotEmpty
   @Size(min=4, max=12)
   @Pattern(regexp = "^[a-z0-9]+$", message = "������ ���ڰ� ȥ�յ� ���̵� �Է��� �ּ���.")
   private String id;
   
   @NotEmpty
   @Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{8,32}", message = "������ ���ڰ� ���Ե� ��й�ȣ�� �Է��� �ּ���. ")
   private String pw;
   
   @NotEmpty
   @Size(min=2, max=8)
   @Pattern(regexp = "^[��-�R]+$", message = "���Ŀ� ���� �ʽ��ϴ�.")
   private String name;
   
   @NotEmpty
   @Size(min=2, max=8)
   @Pattern(regexp = "^[��-�Ra-zA-Z0-9]+$", message = "���Ŀ� ���� �ʽ��ϴ�.")
   private String nickname;
   
   @NotEmpty
   @Email
   private String email;
   
   @NotEmpty
   @Pattern(regexp="^01(?:0|1|[6-9])(?:\\d{3}|\\d{4})\\d{4}$", message = "���Ŀ� ���� �ʽ��ϴ�.")
   private String phone;

}
