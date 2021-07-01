package br.com.devmos.reddit.service;

import static java.util.stream.Collectors.toList;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.devmos.reddit.dto.SubredditDto;
import br.com.devmos.reddit.exceptions.SpringRedditException;
import br.com.devmos.reddit.mapper.SubredditMapper;
import br.com.devmos.reddit.models.Subreddit;
import br.com.devmos.reddit.repository.SubredditRepository;

@Service
public class SubredditService {

    private final SubredditRepository subredditRepository;
    private final SubredditMapper subredditMapper;
    
    public SubredditService(SubredditRepository subredditRepository, SubredditMapper subredditMapper) {
		this.subredditRepository = subredditRepository;
		this.subredditMapper = subredditMapper;
	}

	@Transactional
    public SubredditDto save(SubredditDto subredditDto) {
        Subreddit save = subredditRepository.save(subredditMapper.mapDtoToSubreddit(subredditDto));
        subredditDto.setId(save.getId());
        return subredditDto;
    }

    @Transactional(readOnly = true)
    public List<SubredditDto> getAll() {
        return subredditRepository.findAll()
                .stream()
                .map(subredditMapper::mapSubredditToDto)
                .collect(toList());
    }

    public SubredditDto getSubreddit(Long id) {
        Subreddit subreddit = subredditRepository.findById(id)
                .orElseThrow(() -> new SpringRedditException("No subreddit found with ID - " + id));
        return subredditMapper.mapSubredditToDto(subreddit);
    }
}
