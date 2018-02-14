import { BaseEntity } from './../../shared';

export class BasicIndex implements BaseEntity {
    constructor(
        public id?: number,
        public name?: string,
        public nickname?: string,
        public description?: string,
    ) {
    }
}
