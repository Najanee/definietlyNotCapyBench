export class Subtopic {
    id: number;
    name: string;
    postsIds: number[];
    subscriberIds: number[];
    expirationDate: Date;
    type: string = "subtopic";

    constructor(id: number, name: string, postsIds: number[], subscriberIds: number[], expirationDate: Date) {
        this.id = id;
        this.name = name;
        this.postsIds = postsIds;
        this.subscriberIds = subscriberIds;
        this.expirationDate = expirationDate;
        this.type = "subtopic";
    }
}