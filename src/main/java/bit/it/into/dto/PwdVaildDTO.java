package bit.it.into.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PwdVaildDTO {
	
	@NotEmpty
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-zA-Z]).{8,32}", message="8�� �̻� 32�� �̳��� �ϳ��� ������ ���Ե� ��й�ȣ�� �Է��� �ּ���.")
	private String pw;	
	
	private String id;
}
