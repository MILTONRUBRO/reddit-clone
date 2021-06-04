package br.com.devmos.reddit.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import br.com.devmos.reddit.exceptions.SpringRedditException;
import br.com.devmos.reddit.models.RefreshToken;
import br.com.devmos.reddit.repository.RefreshTokenRepository;

@Service
@Transactional
public class RefreshTokenService {
	
    private final RefreshTokenRepository refreshTokenRepository;
    
    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository) {
		this.refreshTokenRepository = refreshTokenRepository;
	}

	public RefreshToken generateRefreshToken() {
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setCreatedDate(Instant.now());

        return refreshTokenRepository.save(refreshToken);
    }

    void validateRefreshToken(String token) {
        refreshTokenRepository.findByToken(token)
                .orElseThrow(() -> new SpringRedditException("Invalid refresh Token"));
    }

    public void deleteRefreshToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

}
