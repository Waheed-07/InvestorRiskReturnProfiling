import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './fim-accounts.reducer';

export const FimAccountsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const fimAccountsEntity = useAppSelector(state => state.fim.fimAccounts.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="fimAccountsDetailsHeading">
          <Translate contentKey="fimApp.fimFimAccounts.detail.title">FimAccounts</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.id}</dd>
          <dt>
            <span id="accountId">
              <Translate contentKey="fimApp.fimFimAccounts.accountId">Account Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.accountId}</dd>
          <dt>
            <span id="custId">
              <Translate contentKey="fimApp.fimFimAccounts.custId">Cust Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.custId}</dd>
          <dt>
            <span id="relnId">
              <Translate contentKey="fimApp.fimFimAccounts.relnId">Reln Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.relnId}</dd>
          <dt>
            <span id="relnType">
              <Translate contentKey="fimApp.fimFimAccounts.relnType">Reln Type</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.relnType}</dd>
          <dt>
            <span id="operInst">
              <Translate contentKey="fimApp.fimFimAccounts.operInst">Oper Inst</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.operInst}</dd>
          <dt>
            <span id="isAcctNbr">
              <Translate contentKey="fimApp.fimFimAccounts.isAcctNbr">Is Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.isAcctNbr}</dd>
          <dt>
            <span id="bndAcctNbr">
              <Translate contentKey="fimApp.fimFimAccounts.bndAcctNbr">Bnd Acct Nbr</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.bndAcctNbr}</dd>
          <dt>
            <span id="closingId">
              <Translate contentKey="fimApp.fimFimAccounts.closingId">Closing Id</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.closingId}</dd>
          <dt>
            <span id="subSegment">
              <Translate contentKey="fimApp.fimFimAccounts.subSegment">Sub Segment</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.subSegment}</dd>
          <dt>
            <span id="branchCode">
              <Translate contentKey="fimApp.fimFimAccounts.branchCode">Branch Code</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.branchCode}</dd>
          <dt>
            <span id="acctStatus">
              <Translate contentKey="fimApp.fimFimAccounts.acctStatus">Acct Status</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.acctStatus}</dd>
          <dt>
            <span id="ctryCode">
              <Translate contentKey="fimApp.fimFimAccounts.ctryCode">Ctry Code</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.ctryCode}</dd>
          <dt>
            <span id="acctOwners">
              <Translate contentKey="fimApp.fimFimAccounts.acctOwners">Acct Owners</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.acctOwners}</dd>
          <dt>
            <span id="remarks">
              <Translate contentKey="fimApp.fimFimAccounts.remarks">Remarks</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.remarks}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="fimApp.fimFimAccounts.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.createdBy}</dd>
          <dt>
            <span id="createdTs">
              <Translate contentKey="fimApp.fimFimAccounts.createdTs">Created Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsEntity.createdTs ? <TextFormat value={fimAccountsEntity.createdTs} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="fimApp.fimFimAccounts.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.updatedBy}</dd>
          <dt>
            <span id="updatedTs">
              <Translate contentKey="fimApp.fimFimAccounts.updatedTs">Updated Ts</Translate>
            </span>
          </dt>
          <dd>
            {fimAccountsEntity.updatedTs ? <TextFormat value={fimAccountsEntity.updatedTs} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="fimApp.fimFimAccounts.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{fimAccountsEntity.recordStatus}</dd>
        </dl>
        <Button tag={Link} to="/fim/fim-accounts" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/fim/fim-accounts/${fimAccountsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default FimAccountsDetail;
