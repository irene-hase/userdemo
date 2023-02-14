package de.heidelberg.ui.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@AllArgsConstructor
public class Credentials implements Serializable {

        private static final long serialVersionUID = -5554252114058479008L;

        @Getter
        private String password;

        @Getter
        private String username;

        public void clear(){
                password= null;
                username= null;
        }
}

