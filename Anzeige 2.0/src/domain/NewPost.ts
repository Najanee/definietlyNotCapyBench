export class NewPost {
    title: string;
    content: string;
    personId: number;
    topicId: number;
    subtopicId: number | null;

    constructor(title: string, content: string, personId: number, topicId: number, subtopicId?: number) {
        this.title = title;
        this.content = content;
        this.personId = personId;
        this.topicId = topicId;
        this.subtopicId = subtopicId ? subtopicId : null;
    }
}