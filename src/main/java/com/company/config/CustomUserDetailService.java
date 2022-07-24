package com.company.config;

import com.company.entity.CompanyEntity;
import com.company.entity.ProfileEntity;
import com.company.repository.CompanyRepository;
import com.company.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // company or profile(uz_card_.....)
        if (username.startsWith("uz_card")) {
            Optional<ProfileEntity> entity = profileRepository.findByUsername(username);
            if (entity.isEmpty()) {
                throw new UsernameNotFoundException("User Not Found");
            }
            return new CustomUserDetails(entity.get());
        } else { // company
            Optional<CompanyEntity> entity = companyRepository.findByUsername(username);
            if (entity.isEmpty()) {
                throw new UsernameNotFoundException("User Not Found");
            }

            return new CustomUserDetails(entity.get());
        }
    }
}
