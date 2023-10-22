import { Person } from "./Person";
import { Subtopic } from "./Subtopic";
import { Topic } from "./Topic";

export class Post {
    id: number;
    title: string;
    content: string;
    createDate: Date;
    author: Person;
    expirationDate: Date;
    topic: Topic;
    subtopic: Subtopic | undefined;
    subscriberIds: number[];
    type: string = "post";

    constructor(id: number, title: string, 
                content: string, createDate: Date, 
                author: Person, expirationDate: Date, 
                subscriberIds: number[], topic: Topic, 
                subtopic?: Subtopic) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.author = author;
        this.createDate = new Date(createDate);
        this.expirationDate = new Date(expirationDate);
        this.topic = topic;
        this.subtopic = subtopic;
        this.subscriberIds = subscriberIds;
        this.type = "post";
    }
}