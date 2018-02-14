import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BasicIndex } from './basic-index.model';
import { BasicIndexPopupService } from './basic-index-popup.service';
import { BasicIndexService } from './basic-index.service';

@Component({
    selector: 'jhi-basic-index-delete-dialog',
    templateUrl: './basic-index-delete-dialog.component.html'
})
export class BasicIndexDeleteDialogComponent {

    basicIndex: BasicIndex;

    constructor(
        private basicIndexService: BasicIndexService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.basicIndexService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'basicIndexListModification',
                content: 'Deleted an basicIndex'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-basic-index-delete-popup',
    template: ''
})
export class BasicIndexDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private basicIndexPopupService: BasicIndexPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.basicIndexPopupService
                .open(BasicIndexDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
