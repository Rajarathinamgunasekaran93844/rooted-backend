package com.rooted.rooted_backend.api;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api")
public class CatalogController {

    private static final List<Instrument> INSTRUMENTS = List.of(
            new Instrument("yal", "YĀL", "THE YĀL HARP", "A CURVED STRING INSTRUMENT BUILT IN THE SHAPE OF AN ANIMAL", "LISTEN TO THE SAGA", "The ancient harp of the Tamil people, mentioned in Sangam literature, carved with intricate symbolic motifs.", List.of(
                    "The Yāl does not have a fretboard; each string, when plucked, produces a fixed pitch based on its length, tension, and material.",
                    "The wooden bridge, which holds the strings, transmits vibrations to the resonator.",
                    "The body of the Yāl is hollowed out, often from jackfruit or sandalwood, acting as a soundbox that amplifies the vibrations.",
                    "Unlike later Indian string instruments, the Yāl produces a clean, ringing tone with a distinctive harp-like quality.",
                    "The fan-like arrangement of strings allows for both melodic and harmonic playing.",
                    "The overall volume of the sound tends to be very low."
            )),
            new Instrument("kinnaram", "KINNARAM", "THE KINNARAM", "AN ANCIENT BOWED STRING INSTRUMENT", "EXPLORE THE SOUND", "A rare bowed string instrument, its resonance echoing the deep spiritual history of tribal India.", List.of(
                    "The Kinnaram is an ancient bowed string instrument associated with traditional musical practices.",
                    "Its distinctive structure produces a deep and expressive tonal character.",
                    "The instrument has important connections with indigenous and tribal musical traditions.",
                    "Its sound is shaped by the relationship between the bow, strings, and resonating body."
            )),
            new Instrument("panchamuga_vaathiyam", "PANCHAMUGA VAATHIYAM", "PANCHAMUGA VAATHIYAM", "A FIVE-FACED RHYTHMIC INSTRUMENT", "LISTEN TO THE RHYTHM", "The five-faced drum, a rhythmic masterpiece of bronze and skin, producing sounds that mimic the elements of nature.", List.of(
                    "The Panchamuga Vaathiyam is known for its distinctive multiple playing surfaces.",
                    "Its construction allows for a variety of rhythmic and tonal expressions.",
                    "The instrument has strong connections with ceremonial and cultural traditions.",
                    "Different surfaces can create different tonal characteristics during performance."
            )),
            new Instrument("tutari", "TUTARI", "THE TUTARI", "A CURVED BRASS WIND INSTRUMENT", "HEAR THE CALL", "The Tutari is a curved brass instrument that calls across valleys and through cultural epochs.", List.of(
                    "The Tutari is a traditional curved brass wind instrument.",
                    "It produces a powerful and distinctive ceremonial sound.",
                    "Historically, it has been used during celebrations and important announcements.",
                    "Its curved form makes it one of the visually recognisable traditional instruments."
            )),
            new Instrument("parai", "PARAI", "THE PARAI", "AN ANCIENT FRAME DRUM OF TAMIL CULTURE", "FEEL THE RHYTHM", "One of the oldest drums of India, the Parai is the voice of the people, a symbol of resistance and communal celebration.", List.of(
                    "The Parai is one of the oldest percussion instruments associated with Tamil culture.",
                    "It has historically been used for communication, ceremonies, and performance.",
                    "The instrument produces powerful rhythmic patterns when played with sticks.",
                    "Today, the Parai continues to represent cultural identity, expression, and collective memory."
            ))
    );

    private static final List<Collection> ARCHIVES = List.of(
            new Collection("01", "SOUND ARCHIVES", "A living collection of rare recordings, field documentation, oral histories, and endangered musical traditions."),
            new Collection("02", "VISUAL ARCHIVES", "Photographs, moving images, performances, and visual records documenting instruments and their communities."),
            new Collection("03", "INSTRUMENT ARCHIVES", "Stories, forms, materials, and histories of instruments that continue to carry generations of cultural memory."),
            new Collection("04", "ORAL HISTORIES", "Conversations with musicians, makers, elders, and communities who preserve knowledge through lived experience."),
            new Collection("05", "FIELD DOCUMENTATION", "Research notes, journeys, recordings, and observations gathered from the places where these traditions continue."),
            new Collection("06", "CULTURAL MEMORY", "Fragments of stories, practices, rituals, and traditions preserved for future generations.")
    );

    private static final List<ResearchArea> RESEARCH_AREAS = List.of(
            new ResearchArea("01", "FIELD DOCUMENTATION", "Recording endangered musical practices directly within the communities where they continue to live, evolve and survive."),
            new ResearchArea("02", "INSTRUMENT STUDIES", "Examining the construction, materials, acoustics and cultural history of traditional instruments across South India."),
            new ResearchArea("03", "ORAL HISTORIES", "Preserving the voices of musicians, craftspeople, elders and communities whose knowledge has traditionally been passed from generation to generation."),
            new ResearchArea("04", "MUSICAL TRADITIONS", "Studying the contexts in which traditional music is performed, including rituals, celebrations, ceremonies and everyday community life."),
            new ResearchArea("05", "ARCHIVAL RESEARCH", "Connecting contemporary field recordings with historical texts, photographs, manuscripts and existing cultural archives."),
            new ResearchArea("06", "CULTURAL PRESERVATION", "Building accessible digital resources that allow endangered sounds, stories and knowledge to remain available for future generations.")
    );

    private static final List<Video> VIDEOS = List.of(
            new Video("VIDEO_ID_1", "ROOTED — THE MUSICAL JOURNEY", "A journey into India's traditional musical heritage and the communities keeping these sounds alive.", null),
            new Video("VIDEO_ID_2", "THE SOUND OF THE YĀL", "Discover the history, construction and cultural memory behind the ancient Yāl harp.", null),
            new Video("VIDEO_ID_3", "THE PARAI — VOICE OF THE PEOPLE", "Exploring the rhythm, history and cultural significance of the Parai.", null),
            new Video("VIDEO_ID_4", "MEET THE MUSICIANS", "Stories from the people who continue to carry traditional musical knowledge forward.", null)
    );

    @GetMapping("/health")
    public Health health() {
        return new Health("ok");
    }

    @GetMapping("/catalog/instruments")
    public List<Instrument> instruments() {
        return INSTRUMENTS;
    }

    @GetMapping("/catalog/instruments/{slug}")
    public Instrument instrument(@PathVariable String slug) {
        return INSTRUMENTS.stream()
                .filter(instrument -> instrument.slug().equalsIgnoreCase(slug))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Instrument not found."));
    }

    @GetMapping("/catalog/archives")
    public List<Collection> archives() {
        return ARCHIVES;
    }

    @GetMapping("/catalog/research-areas")
    public List<ResearchArea> researchAreas() {
        return RESEARCH_AREAS;
    }

    @GetMapping("/catalog/videos")
    public List<Video> videos() {
        return VIDEOS;
    }

    public record Health(String status) {
    }

    public record Instrument(String slug, String name, String title, String subtitle, String actionLabel,
                             String description, List<String> details) {
    }

    public record Collection(String number, String title, String description) {
    }

    public record ResearchArea(String number, String title, String description) {
    }

    public record Video(String id, String title, String description, String youtubeId) {
    }
}
