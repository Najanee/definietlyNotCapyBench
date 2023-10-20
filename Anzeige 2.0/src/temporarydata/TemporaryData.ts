import { Person } from "../domain/Person";
import { Post } from "../domain/Post";

const content = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.";
const tags : string[] = ["Sprzedam", "Kapibary i ja", "Projekty", "Oddam", "Bench"];
  
const person1: Person = new Person(1, "Jolanta Kołtun", "");
const person2: Person = new Person(2, "Henryk Kwaśnica", "");
const person3: Person = new Person(3, "Anna Maria Bigos", "");

const post1: Post = new Post(1, "Sprzedam dwuletnią Kapibarę", content, person1, new Date(Date.now()));
const post2: Post = new Post(2, "Kupię dwuletnią Kapibarę", content, person2, new Date(Date.now()));
const post3: Post = new Post(3, "Nie kupię dwuletniej Kapibary", content, person3, new Date(Date.now()));

const posts: Post[] = [post1, post2, post3];

export { content, tags, person1, person2, person3, post1, post2, post3, posts }