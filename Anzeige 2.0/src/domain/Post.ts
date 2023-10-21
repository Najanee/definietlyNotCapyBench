import { Person } from "./Person";
import { Subtopic } from "./Subtopic";
import { Topic } from "./Topic";

export class Post {
    id: number;
    title: string;
    content: string;
    author: Person;
    createdDate: Date;
    subtopic?: Subtopic | undefined;
    topic: Topic;

    constructor(id: number, title: string, 
                content: string, author: Person, 
                createdDate: Date, topic: Topic, subtopic?: Subtopic) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createdDate = createdDate;
        this.subtopic = subtopic;
        this.topic = topic;
    }
}