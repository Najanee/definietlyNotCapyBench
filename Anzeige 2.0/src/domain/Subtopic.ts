import { Topic } from "./Topic";
export class Subtopic {
    id: number;
    name: string;
    topic: Topic;

    constructor(id: number, name: string, topic: Topic) {
        this.id = id;
        this.name = name;
        this.topic = topic;
    }
}