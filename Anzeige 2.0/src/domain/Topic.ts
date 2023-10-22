import { Subtopic } from "./Subtopic";

export class Topic {
    id: number;
    name: string;
    subtopics: Subtopic[];
    postIds: number[];
    subscriberIds: number[];
    expirationDate: Date;

    constructor(id: number, name: string, subtopics: Subtopic[], postIds: number[], subsciberIds: number[], expirationDate: Date) {
        this.id = id;
        this.name = name;
        this.subtopics = subtopics;
        this.postIds = postIds;
        this.subscriberIds = subsciberIds;
        this.expirationDate = expirationDate;
    }
}