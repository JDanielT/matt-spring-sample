package matt.k.springsample;

import jakarta.websocket.server.PathParam;
import lombok.RequiredArgsConstructor;
import matt.k.springsample.model.Fruit;
import matt.k.springsample.repository.FruitRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fruits")
@RequiredArgsConstructor
public class FruitController {

    private final FruitRepository fruitRepository;

    @PostMapping
    private Fruit addNew(@RequestBody Fruit fruit) {
        return fruitRepository.save(fruit);
    }

    @PutMapping("/{id}")
    private Fruit addNew(@PathVariable Long id, @RequestBody Fruit fruit) {
        var existing = fruitRepository.findById(id).orElseThrow(() -> new RuntimeException("Record not found"));
        existing.setName(fruit.getName());
        existing.setColor(fruit.getColor());
        return fruitRepository.save(existing);
    }

    @GetMapping
    public Page<Fruit> getPaginatedResponse(@RequestParam int pageNumber, @RequestParam int pageSize) {
        return fruitRepository.findAll(PageRequest.of(pageNumber, pageSize));
    }

}
