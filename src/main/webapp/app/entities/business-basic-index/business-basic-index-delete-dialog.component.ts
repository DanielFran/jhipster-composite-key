import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { BusinessBasicIndex } from './business-basic-index.model';
import { BusinessBasicIndexPopupService } from './business-basic-index-popup.service';
import { BusinessBasicIndexService } from './business-basic-index.service';

@Component({
    selector: 'jhi-business-basic-index-delete-dialog',
    templateUrl: './business-basic-index-delete-dialog.component.html'
})
export class BusinessBasicIndexDeleteDialogComponent {

    businessBasicIndex: BusinessBasicIndex;

    constructor(
        private businessBasicIndexService: BusinessBasicIndexService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.businessBasicIndexService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'businessBasicIndexListModification',
                content: 'Deleted an businessBasicIndex'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-business-basic-index-delete-popup',
    template: ''
})
export class BusinessBasicIndexDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private businessBasicIndexPopupService: BusinessBasicIndexPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.businessBasicIndexPopupService
                .open(BusinessBasicIndexDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
