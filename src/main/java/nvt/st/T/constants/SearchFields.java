package nvt.st.T.constants;

import java.util.List;

public interface SearchFields {
    List<String> USER = List.of(
            "username",
            "fullName",
            "email",
            "role"
    );

    List<String> POST = List.of(
            "user.fullname",
            "content",
            "pages.name"
    );


}
