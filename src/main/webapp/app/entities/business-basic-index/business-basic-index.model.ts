import { BaseEntity } from './../../shared';

export class BusinessBasicIndex implements BaseEntity {
    constructor(
        public id?: number,
        public year?: number,
        public month?: number,
        public value?: number,
        public businessId?: number,
        public basicIndexId?: number,
    ) {
    }
}
