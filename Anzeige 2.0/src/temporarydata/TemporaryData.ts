import { Person } from "../domain/Person";
import { Post } from "../domain/Post";
import { Subtopic } from "../domain/Subtopic";
import { Topic } from "../domain/Topic";

const content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
const topics: Topic[] = [new Topic(1,"Sprzedam"), new Topic(2, "Kapibary i ja"), new Topic(3,"Projekty"), new Topic(4, "Oddam"), new Topic(5, "Bench")];
const subtopic: Subtopic = new Subtopic(1, "Kapibara", new Topic(4, "Oddam"))

const person1: Person = new Person(1, "Jolanta Kołtun", "C:\\hackaton\\definitelyNotCapyBench_front\\definietlyNotCapyBench\\Anzeige 2.0\\images\\kapibara1.png");
const person2: Person = new Person(2, "Henryk Kwaśnica", "https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/kapibara2.png?raw=true");
const person3: Person = new Person(3, "Anna Maria Bigos", "https://github.com/MayPaw/definietlyNotCapyBench/blob/frontend-development/Anzeige%202.0/images/kapibara3.png?raw=true");

const post1: Post = new Post(1, "Sprzedam dwuletnią Kapibarę", content, person1, new Date(Date.now()), topics[0], undefined);
const post2: Post = new Post(2, "Kupię dwuletnią Kapibarę", content, person2, new Date(Date.now()), topics[0], undefined);
const post3: Post = new Post(3, "Nie oddam dwuletniej Kapibary", content, person3, new Date(Date.now()), topics[3], subtopic);
const post4: Post = new Post(4, "Sprzedam dwuletnią Kapibarę", content, person1, new Date(Date.now()), topics[0], undefined);
const post5: Post = new Post(5, "Kupię dwuletnią Kapibarę", content, person2, new Date(Date.now()), topics[0], undefined);
const post6: Post = new Post(6, "Nie oddam dwuletniej Kapibary", content, person3, new Date(Date.now()), topics[3], subtopic);

const posts: Post[] = [post1, post2, post3, post4, post5, post6];

export { content, topics, posts }