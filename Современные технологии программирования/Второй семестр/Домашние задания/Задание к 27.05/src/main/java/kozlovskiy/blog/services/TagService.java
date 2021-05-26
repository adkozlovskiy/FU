package kozlovskiy.blog.services;

import kozlovskiy.blog.models.Tag;
import kozlovskiy.blog.repo.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepo tagRepo;

    @Autowired
    public TagService(TagRepo tagRepo) {
        this.tagRepo = tagRepo;
    }

    public void createTag(Tag tag) {
        tagRepo.save(tag);
    }

    public Tag getTagById(Long id) {
        return tagRepo.findById(id).orElse(null);
    }

    public List<Tag> getTags() {
        return tagRepo.findAll();
    }

    public boolean updateTag(Tag tag, Long id) {
        if (getTagById(id) != null) {
            tag.setId(id);
            tagRepo.save(tag);
            return true;
        }

        return false;
    }

    public boolean deleteTag(Long id) {
        if (getTagById(id) != null) {
            tagRepo.deleteById(id);
            return true;
        }
        return false;
    }
}