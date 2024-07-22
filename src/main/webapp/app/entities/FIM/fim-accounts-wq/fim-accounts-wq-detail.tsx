import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-accounts-wq.reducer';

export const FimAccountsWqDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimAccountsWqEntity = useAppSelector(state => state.fim.fimAccountsWq.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimAccountsWqDetailsHeading">
          <Translate contentKey="fimApp.fimFimAccountsWq.detail.title">FimAccountsWq</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.id}</dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="fimApp.fimFimAccountsWq.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.accountId}</dd>
          <dt>
            <span id="custId">
              <Translate contentKey="fimApp.fimFimAccountsWq.custId">Cust Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.custId}</dd>
          <dt>
            <span id="relnId">
              <Translate contentKey="fimApp.fimFimAccountsWq.relnId">Reln Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.relnId}</dd>
          <dt>
            <span id="relnType">
              <Translate contentKey="fimApp.fimFimAccountsWq.relnType">Reln Type</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.relnType}</dd>
          <dt>
            <span id="operInst">
              <Translate contentKey="fimApp.fimFimAccountsWq.operInst">Oper Inst</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.operInst}</dd>
          <dt>
            <span id="isAcctNbr">
              <Translate contentKey="fimApp.fimFimAccountsWq.isAcctNbr">Is Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.isAcctNbr}</dd>
          <dt>
            <span id="bndAcctNbr">
              <Translate contentKey="fimApp.fimFimAccountsWq.bndAcctNbr">Bnd Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.bndAcctNbr}</dd>
          <dt>
            <span id="closingId">
              <Translate contentKey="fimApp.fimFimAccountsWq.closingId">Closing Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.closingId}</dd>
          <dt>
            <span id="subSegment">
              <Translate contentKey="fimApp.fimFimAccountsWq.subSegment">Sub Segment</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.subSegment}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="fimApp.fimFimAccountsWq.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.branchCode}</dd>
          <dt>
            <span id="acctStatus">
              <Translate contentKey="fimApp.fimFimAccountsWq.acctStatus">Acct Status</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.acctStatus}</dd>
          <dt>
            <span id="ctryCode">
              <Translate contentKey="fimApp.fimFimAccountsWq.ctryCode">Ctry Code</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.ctryCode}</dd>
          <dt>
            <span id="acctOwners">
              <Translate contentKey="fimApp.fimFimAccountsWq.acctOwners">Acct Owners</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.acctOwners}</dd>
          <dt>
            <span id="remarks">
              <Translate contentKey="fimApp.fimFimAccountsWq.remarks">Remarks</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.remarks}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimAccountsWq.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimAccountsWq.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsWqEntity.createdTs ? (
              <TextFormat value={fimAccountsWqEntity.createdTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimAccountsWq.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimAccountsWq.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsWqEntity.updatedTs ? (
              <TextFormat value={fimAccountsWqEntity.updatedTs} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimAccountsWq.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.recordStatus}</dd>
          <dt>
            <span id="uploadRemark">
              <Translate contentKey="fimApp.fimFimAccountsWq.uploadRemark">Upload Remark</Translate>
            </span>
          </dt>
          <dd>{fimAccountsWqEntity.uploadRemark}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-accounts-wq" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-accounts-wq/${fimAccountsWqEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimAccountsWqDetail;
