import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { BasicIndex } from './basic-index.model';
import { BasicIndexService } from './basic-index.service';

@Injectable()
export class BasicIndexPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private basicIndexService: BasicIndexService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.basicIndexService.find(id)
                    .subscribe((basicIndexResponse: HttpResponse<BasicIndex>) => {
                        const basicIndex: BasicIndex = basicIndexResponse.body;
                        this.ngbModalRef = this.basicIndexModalRef(component, basicIndex);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.basicIndexModalRef(component, new BasicIndex());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    basicIndexModalRef(component: Component, basicIndex: BasicIndex): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.basicIndex = basicIndex;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
