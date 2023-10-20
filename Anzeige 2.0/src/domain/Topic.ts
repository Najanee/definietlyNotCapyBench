import { Subtopic } from "./Subtopic";

export class Topic {
    id: number;
    name: string;
    subtopics: Subtopic[];

    constructor(id: number, name: string, subtopics: Subtopic[]) {
        this.id = id;
        this.name = name;
        this.subtopics = subtopics;
    }
}